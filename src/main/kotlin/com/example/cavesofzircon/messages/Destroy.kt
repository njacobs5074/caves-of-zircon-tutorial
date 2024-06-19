package com.example.cavesofzircon.messages

import com.example.cavesofzircon.extensions.GameEntity
import com.example.cavesofzircon.extensions.GameMessage
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

/**
 * @author nick
 * @since 2024/06/19
 */
data class Destroy(
  override val context: GameContext,
  override val source: Entity<EntityType, GameContext>,
  val target: GameEntity<EntityType>,
  val cause: String = "natural causes."
) : GameMessage
