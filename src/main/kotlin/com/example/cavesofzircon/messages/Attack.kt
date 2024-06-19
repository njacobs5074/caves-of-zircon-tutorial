package com.example.cavesofzircon.messages

import com.example.cavesofzircon.attributes.types.Combatant
import com.example.cavesofzircon.extensions.GameEntity
import com.example.cavesofzircon.world.GameContext

/**
 * @author nick
 * @since 2024/06/18
 */
data class Attack(
  override val context: GameContext,
  override val source: GameEntity<Combatant>,
  override val target: GameEntity<Combatant>
) : EntityAction<Combatant, Combatant>
