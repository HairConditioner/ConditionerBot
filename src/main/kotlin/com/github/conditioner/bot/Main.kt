package com.github.conditioner.bot

import com.github.conditioner.bot.listener.InviteListenerAdapter
import com.github.conditioner.bot.listener.MemeListenerAdapter
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import java.net.InetAddress

fun main() {
    try {
        val jda: JDA = JDABuilder.createDefault("ODk5NDQyMTA2NDQ2MjgyODEy.GshBjz.zQeG_nC_kZMXqAQ32QKB-a6X8PDqAoK2K7_9AQ").build()

        jda.presence.setStatus(OnlineStatus.IDLE)
        jda.presence.activity = Activity.playing(InetAddress.getLocalHost().hostName)
        jda.addEventListener(InviteListenerAdapter())
        jda.addEventListener(MemeListenerAdapter())
    } catch (ignored: Exception) {}
}