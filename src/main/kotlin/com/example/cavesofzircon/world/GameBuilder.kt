package com.example.cavesofzircon.world

import com.example.cavesofzircon.GameConfig.DUNGEON_LEVELS
import com.example.cavesofzircon.GameConfig.LOG_AREA_HEIGHT
import com.example.cavesofzircon.GameConfig.SIDEBAR_WIDTH
import com.example.cavesofzircon.GameConfig.WINDOW_HEIGHT
import com.example.cavesofzircon.GameConfig.WINDOW_WIDTH
import com.example.cavesofzircon.GameConfig.WORLD_SIZE
import com.example.cavesofzircon.attributes.types.Player
import com.example.cavesofzircon.builders.EntityFactory
import com.example.cavesofzircon.builders.WorldBuilder
import com.example.cavesofzircon.extensions.GameEntity
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D

/**
 * @author nick
 * @since 2024/06/16
 */
class GameBuilder(val worldSize: Size3D) {
  private val visibleSize = Size3D.create(
    xLength = WINDOW_WIDTH - SIDEBAR_WIDTH,
    yLength = WINDOW_HEIGHT - LOG_AREA_HEIGHT,
    zLength = 1
  )

  val world = WorldBuilder(worldSize)
    .makeCaves()
    .build(visibleSize = visibleSize)

  fun buildGame(): Game {
    prepareWorld()
    val player = addPlayer()

    return Game.create(player, world)
  }

  private fun prepareWorld() = also {
    world.scrollUpBy(world.actualSize.zLength)
  }

  private fun addPlayer(): GameEntity<Player> {
    val player = EntityFactory.newPlayer()
    world.addAtEmptyPosition(
      player,
      offset = Position3D.create(0, 0, DUNGEON_LEVELS - 1),
      size = world.visibleSize.copy(zLength = 0)
    )
    return player
  }

  companion object {
    fun create() = GameBuilder(WORLD_SIZE).buildGame()
  }
}