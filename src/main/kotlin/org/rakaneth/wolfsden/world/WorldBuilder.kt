package org.rakaneth.wolfsden.world

import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.data.impl.Size3D
import org.rakaneth.wolfsden.blocks.GameBlock

class WorldBuilder (private val worldSize: Size3D){
    private val width = worldSize.xLength
    private val height = worldSize.zLength
    private var blocks: MutableMap<Position3D, GameBlock> = mutableMapOf()
}