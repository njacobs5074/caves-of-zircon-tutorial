package com.example.cavesofzircon.blocks

import com.example.cavesofzircon.builders.GameTileRepository.EMPTY
import com.example.cavesofzircon.builders.GameTileRepository.FLOOR
import com.example.cavesofzircon.builders.GameTileRepository.PLAYER
import com.example.cavesofzircon.builders.GameTileRepository.WALL
import com.example.cavesofzircon.extensions.GameEntity
import com.example.cavesofzircon.extensions.occupiesBlock
import com.example.cavesofzircon.extensions.tile
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

/**
 * @author nick
 * @since 2024/06/15
 */
class GameBlock(
  private var defaultTile: Tile = FLOOR,
  private val currentEntities: MutableList<GameEntity<EntityType>> = mutableListOf()
) : BaseBlock<Tile>(
  emptyTile = EMPTY,
  tiles = persistentMapOf(BlockTileType.CONTENT to defaultTile)
) {

  init {
    updateContent()
  }

  companion object {
    fun createWith(entity: GameEntity<EntityType>) = GameBlock(
      currentEntities = mutableListOf(entity)
    )
  }

  val isFloor: Boolean
    get() = content == FLOOR

  val isWall: Boolean
    get() = content == WALL

  val isEmptyFloor: Boolean
    get() = currentEntities.isEmpty()

  val entities: Iterable<GameEntity<EntityType>>
    get() = currentEntities.toList()

  val occupier: Maybe<GameEntity<EntityType>>
    get() = Maybe.ofNullable(currentEntities.firstOrNull { it.occupiesBlock })

  val isOccupied: Boolean
    get() = occupier.isPresent

  fun addEntity(entity: GameEntity<EntityType>) {
    currentEntities.add(entity)
    updateContent()
  }

  fun removeEntity(entity: GameEntity<EntityType>) {
    currentEntities.remove(entity)
    updateContent()
  }

  private fun updateContent() {
    val entityTiles = currentEntities.map { it.tile }
    content = when {
      entityTiles.contains(PLAYER) ->
        PLAYER

      entityTiles.isNotEmpty() ->
        entityTiles.first()

      else -> defaultTile
    }
  }
}