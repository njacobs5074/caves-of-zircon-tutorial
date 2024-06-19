package com.example.cavesofzircon.attributes.types

import com.example.cavesofzircon.attributes.CombatStats
import com.example.cavesofzircon.extensions.GameEntity
import org.hexworks.amethyst.api.entity.EntityType

interface Combatant : EntityType

val GameEntity<Combatant>.combatStats: CombatStats
  get() = findAttribute(CombatStats::class).get()