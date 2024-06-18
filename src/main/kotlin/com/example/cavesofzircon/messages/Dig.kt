package com.example.cavesofzircon.messages

import com.example.cavesofzircon.extensions.GameEntity
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

/**
 * @author nick
 * @since 2024/06/17
 */
data class Dig(
  override val context: GameContext,
  override val source: Entity<EntityType, GameContext>,
  override val target: GameEntity<EntityType>
) : EntityAction<EntityType, EntityType>
