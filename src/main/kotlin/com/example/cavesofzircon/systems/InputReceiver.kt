package com.example.cavesofzircon.systems

import com.example.cavesofzircon.attributes.types.Player
import com.example.cavesofzircon.extensions.GameEntity
import com.example.cavesofzircon.extensions.position
import com.example.cavesofzircon.messages.MoveDown
import com.example.cavesofzircon.messages.MoveTo
import com.example.cavesofzircon.messages.MoveUp
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

/**
 * @author nick
 * @since 2024/06/16
 */
object InputReceiver : BaseBehavior<GameContext>() {
  private val logger = LoggerFactory.getLogger(this::class)

  override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
    val (_, _, uiEvent, player) = context
    val currentPos = player.position
    if (uiEvent is KeyboardEvent) {
      when (uiEvent.code) {
        KeyCode.KEY_H -> player.moveTo(
          currentPos.withRelativeX(-1),
          context
        )

        KeyCode.KEY_J -> player.moveTo(
          currentPos.withRelativeY(1),
          context
        )

        KeyCode.KEY_K -> player.moveTo(
          currentPos.withRelativeY(-1),
          context
        )

        KeyCode.KEY_L -> player.moveTo(
          currentPos.withRelativeX(1),
          context
        )

        KeyCode.DOWN -> player.moveDown(context)
        KeyCode.UP -> player.moveUp(context)

        else -> {
          logger.debug("UI Event ($uiEvent) does not have a corresponding command; it is ignored.")
        }
      }
    }
    return true
  }

  private suspend fun GameEntity<Player>.moveTo(position: Position3D, context: GameContext) {
    receiveMessage(MoveTo(context, this, position))
  }

  private suspend fun GameEntity<Player>.moveUp(context: GameContext) {
    receiveMessage(MoveUp(context, this))
  }

  private suspend fun GameEntity<Player>.moveDown(context: GameContext) {
    receiveMessage(MoveDown(context, this))
  }
}