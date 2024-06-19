package com.example.cavesofzircon.attributes.types

import org.hexworks.amethyst.api.base.BaseEntityType

/**
 * @author nick
 * @since 2024/06/16
 */
object Player : BaseEntityType(name = "player"), Combatant

object Wall : BaseEntityType(name = "wall")

object Fungus : BaseEntityType(name = "fungus"), Combatant