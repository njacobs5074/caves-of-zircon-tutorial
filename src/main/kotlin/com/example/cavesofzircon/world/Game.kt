package com.example.cavesofzircon.world

import com.example.cavesofzircon.attributes.types.Player
import com.example.cavesofzircon.extensions.GameEntity

/**
 * @author nick
 * @since 2024/06/15
 */
class Game(
  val world: World,
  val player: GameEntity<Player>
  ) {
  companion object {
    fun create(
      player: GameEntity<Player>,
      world: World
    ) = Game(world, player)
  }
}