package com.example.cavesofzircon.builders

import com.example.cavesofzircon.attributes.*
import com.example.cavesofzircon.attributes.flags.BlockOccupier
import com.example.cavesofzircon.attributes.types.*
import com.example.cavesofzircon.builders.GameTileRepository.PLAYER
import com.example.cavesofzircon.messages.Attack
import com.example.cavesofzircon.messages.Dig
import com.example.cavesofzircon.systems.*
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType

/**
 * @author nick
 * @since 2024/06/16
 */

fun <T : EntityType> newGameEntityOfType(
  type: T,
  init: EntityBuilder<T, GameContext>.() -> Unit
) = newEntityOfType(type, init)

object EntityFactory {
  fun newPlayer() = newGameEntityOfType(Player) {
    attributes(
      EntityPosition(),
      EntityTile(PLAYER),
      EntityActions(Dig::class, Attack::class),
      CombatStats.create(
        maxHp = 100,
        attackValue = 10,
        defenseValue = 5
      )
    )
    behaviors(InputReceiver)
    facets(Movable, CameraMover, StairAscender, StairDescender)
  }

  fun newWall() = newGameEntityOfType(Wall) {
    attributes(
      EntityPosition(),
      BlockOccupier,
      EntityTile(GameTileRepository.WALL)
    )
    facets(Diggable)
  }

  fun newFungus(fungusSpread: FungusSpread = FungusSpread()) = newGameEntityOfType(Fungus) {
    attributes(
      BlockOccupier,
      EntityPosition(),
      EntityTile(GameTileRepository.FUNGUS),
      fungusSpread,
      CombatStats.create(
        maxHp = 10,
        attackValue = 0,
        defenseValue = 0
      )
    )
    facets(Attackable, Destructible)
    behaviors(FungusGrowth)
  }

  fun newStairsDown() = newGameEntityOfType(StairsDown) {
    attributes(
      EntityTile(GameTileRepository.STAIRS_DOWN),
      EntityPosition()
    )
  }

  fun newStairsUp() = newGameEntityOfType(StairsUp) {
    attributes(
      EntityTile(GameTileRepository.STAIRS_UP),
      EntityPosition()
    )
  }
}