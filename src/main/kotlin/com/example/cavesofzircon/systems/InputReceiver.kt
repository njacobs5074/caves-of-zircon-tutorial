package com.example.cavesofzircon.systems

import com.example.cavesofzircon.extensions.position
import com.example.cavesofzircon.messages.MoveTo
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

/**
 * @author nick
 * @since 2024/06/16
 */
object InputReceiver : BaseBehavior<GameContext>() {
  override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
    val (_, _, uiEvent, player) = context
    val currentPos = player.position
    if (uiEvent is KeyboardEvent) {
      val newPosition = when (uiEvent.code) {
        KeyCode.LEFT, KeyCode.KEY_H -> currentPos.withRelativeX(-1)
        KeyCode.DOWN, KeyCode.KEY_J -> currentPos.withRelativeY(1)
        KeyCode.UP, KeyCode.KEY_K -> currentPos.withRelativeY(-1)
        KeyCode.RIGHT, KeyCode.KEY_L -> currentPos.withRelativeX(1)
        else -> {
          currentPos
        }
      }
      player.receiveMessage(MoveTo(context, player, newPosition))
    }
    return true
  }
}