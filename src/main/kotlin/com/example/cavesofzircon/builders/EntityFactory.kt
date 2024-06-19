package com.example.cavesofzircon.builders

import com.example.cavesofzircon.attributes.EntityActions
import com.example.cavesofzircon.attributes.EntityPosition
import com.example.cavesofzircon.attributes.EntityTile
import com.example.cavesofzircon.attributes.FungusSpread
import com.example.cavesofzircon.attributes.flags.BlockOccupier
import com.example.cavesofzircon.attributes.types.Fungus
import com.example.cavesofzircon.attributes.types.Player
import com.example.cavesofzircon.attributes.types.Wall
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
      EntityActions(Dig::class, Attack::class)
    )
    behaviors(InputReceiver)
    facets(Movable, CameraMover)
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
      fungusSpread
    )
    facets(Attackable)
    behaviors(FungusGrowth)
  }
}