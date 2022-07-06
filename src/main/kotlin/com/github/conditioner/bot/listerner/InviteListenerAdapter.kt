package com.github.conditioner.bot.listener

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color
import java.text.SimpleDateFormat
import java.util.*

class InviteListenerAdapter : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg: List<String> = event.message.contentRaw.split(" ")
        val date = Date()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        if (msg[0] == "!invite" && msg.size == 1) {
            event.channel.sendMessage("To generate an invite link, run ***!invite create***").queue()
        } else if (msg[0] == "!invite" && msg.size == 2 && msg[1] == "create") {
            val builder = EmbedBuilder()

            builder.setTitle("Link generator")
            builder.setColor(Color.BLUE)
            builder.addField("Message", "Hey, a new link has been generated:", false)
            builder.addField("Link", event.textChannel.createInvite().complete().url, false)
            builder.setFooter("Request was made by ${event.member!!.user.asTag} at ${dateFormat.format(date)}", event.guild.iconUrl)

            event.channel.sendMessageEmbeds(builder.build()).queue()
        }
    }
}
