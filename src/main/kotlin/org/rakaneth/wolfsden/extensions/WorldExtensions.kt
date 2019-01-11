package org.rakaneth.wolfsden.extensions

import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.api.data.impl.Position3D
import org.rakaneth.wolfsden.blocks.GameBlock
import org.rakaneth.wolfsden.world.World

fun World.whenHasBlockAt(pos: Position3D, fn: (GameBlock) -> Unit) {
    fetchBlockAt(pos).map(fn)
}