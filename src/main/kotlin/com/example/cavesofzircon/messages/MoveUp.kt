package com.example.cavesofzircon.messages

import com.example.cavesofzircon.extensions.GameMessage
import com.example.cavesofzircon.world.GameContext
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

/**
 * @author nick
 * @since 2024/06/20
 */
data class MoveUp(
  override val context: GameContext,
  override val source: Entity<EntityType, GameContext>
) : GameMessage
