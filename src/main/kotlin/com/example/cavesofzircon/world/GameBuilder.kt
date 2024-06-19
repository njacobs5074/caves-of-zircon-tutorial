package com.example.cavesofzircon.world

import com.example.cavesofzircon.GameConfig.DUNGEON_LEVELS
import com.example.cavesofzircon.GameConfig.FUNGI_PER_LEVEL
import com.example.cavesofzircon.GameConfig.LOG_AREA_HEIGHT
import com.example.cavesofzircon.GameConfig.SIDEBAR_WIDTH
import com.example.cavesofzircon.GameConfig.WINDOW_HEIGHT
import com.example.cavesofzircon.GameConfig.WINDOW_WIDTH
import com.example.cavesofzircon.GameConfig.WORLD_SIZE
import com.example.cavesofzircon.attributes.types.Player
import com.example.cavesofzircon.builders.EntityFactory
import com.example.cavesofzircon.builders.WorldBuilder
import com.example.cavesofzircon.extensions.GameEntity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.Size3D

/**
 * @author nick
 * @since 2024/06/16
 */
class GameBuilder(private val worldSize: Size3D) {
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
    addFungi()

    return Game.create(player, world)
  }

  private fun prepareWorld() = also {
    world.scrollUpBy(world.actualSize.zLength)
  }

  private fun addPlayer(): GameEntity<Player> {
    return EntityFactory.newPlayer().addToWorld(
      atLevel = DUNGEON_LEVELS - 1,
      atArea = world.visibleSize.to2DSize()
    )
  }

  private fun addFungi() = also {
    repeat(world.actualSize.zLength) { level ->
      repeat(FUNGI_PER_LEVEL) {
        EntityFactory.newFungus().addToWorld(level)
      }
    }
  }

  private fun <T : EntityType> GameEntity<T>.addToWorld(
    atLevel: Int,
    atArea: Size = world.actualSize.to2DSize()
  ): GameEntity<T> {
    world.addAtEmptyPosition(
      this,
      offset = Position3D.defaultPosition().withZ(atLevel),
      size = Size3D.from2DSize(atArea)
    )
    return this
  }

  companion object {
    fun create() = GameBuilder(WORLD_SIZE).buildGame()
  }
}