package com.example.cavesofzircon.systems

import com.example.cavesofzircon.attributes.types.Player
import com.example.cavesofzircon.extensions.position
import com.example.cavesofzircon.messages.MoveCamera
import com.example.cavesofzircon.messages.MoveTo
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.MessageResponse
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

/**
 * @author nick
 * @since 2024/06/16
 */
object Movable : BaseFacet<GameContext, MoveTo>(MoveTo::class) {
  override suspend fun receive(message: MoveTo): Response {
    val (context, entity, position) = message
    val world = context.world
    val previousPosition = entity.position

    return if (world.moveEntity(entity, position)) {
      return if (entity.type == Player) {
        MessageResponse(
          MoveCamera(
            context = context,
            source = entity,
            previousPosition = previousPosition
          )
        )
      } else {
        Consumed
      }
    } else {
      Pass
    }
  }
}