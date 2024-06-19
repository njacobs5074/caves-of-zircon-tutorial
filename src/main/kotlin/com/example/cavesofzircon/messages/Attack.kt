package com.example.cavesofzircon.messages

import com.example.cavesofzircon.extensions.GameEntity
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

/**
 * @author nick
 * @since 2024/06/18
 */
data class Attack(
  override val context: GameContext,
  override val source: GameEntity<EntityType>,
  override val target: GameEntity<EntityType>
) : EntityAction<EntityType, EntityType>
