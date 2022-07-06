package com.github.conditioner.bot.listener

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.awt.Color
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MemeListenerAdapter : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg: List<String> = event.message.contentRaw.split(" ")
        val parser = JSONParser()

        var postLink = ""
        var title = ""
        var url = ""

        try {
            if (msg[0] == "!meme") {
                val memeUrl = URL("https://meme-api.herokuapp.com/gimme")
                val br = BufferedReader(InputStreamReader(memeUrl.openConnection().getInputStream()))
                val line: String = br.readLine()

                val array = JSONArray()

                array.add(parser.parse(line))
                for (any in array) {
                    val obj: JSONObject = any as JSONObject

                    postLink = obj["postLink"] as String
                    title = obj["title"] as String
                    url = obj["url"] as String
                }
                br.close()
                event.message.delete().queue()
                val builder = EmbedBuilder().setTitle(title, postLink).setImage(url).setColor(Color.ORANGE)

                event.channel.sendMessageEmbeds(builder.build()).queue()
            }
        } catch (e: Exception) {
            event.channel.sendMessage("***An error has occurred, please try again later!").queue()
        }
    }
}