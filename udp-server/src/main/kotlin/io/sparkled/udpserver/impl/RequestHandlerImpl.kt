package io.sparkled.udpserver.impl

import io.sparkled.music.PlaybackStateService
import io.sparkled.persistence.setting.SettingPersistenceService
import io.sparkled.udpserver.RequestHandler
import io.sparkled.udpserver.impl.command.GetFrameCommand
import io.sparkled.udpserver.impl.command.GetStagePropCodesCommand
import io.sparkled.udpserver.impl.command.GetVersionCommand
import io.sparkled.udpserver.impl.command.SubscribeCommand
import io.sparkled.udpserver.impl.subscriber.UdpClientSubscribers
import java.net.DatagramPacket
import java.net.DatagramSocket
import javax.inject.Singleton
import org.slf4j.LoggerFactory
import java.net.InetAddress

@Singleton
class RequestHandlerImpl(
    private val playbackStateService: PlaybackStateService,
    private val settingPersistenceService: SettingPersistenceService,
    subscribers: UdpClientSubscribers
) : RequestHandler {

    private val commands = mapOf(
        GetFrameCommand.KEY to GetFrameCommand(),
        GetStagePropCodesCommand.KEY to GetStagePropCodesCommand(),
        GetVersionCommand.KEY to GetVersionCommand(),
        SubscribeCommand.KEY to SubscribeCommand(subscribers)
    )

    override fun handle(serverSocket: DatagramSocket, receivePacket: DatagramPacket, message: String) {
        try {
            val args = message.split(":")
            val response = getResponse(receivePacket.address, receivePacket.port, args)
            if (response != null) {
                respond(serverSocket, receivePacket, response)
            }
        } catch (e: Exception) {
            logger.error("Failed to handle response for message '$message': ${e.message}")
        }
    }

    private fun getResponse(ipAddress: InetAddress, port: Int, args: List<String>): ByteArray? {
        val playbackState = playbackStateService.getPlaybackState()
        val settings = settingPersistenceService.settings

        val command = args[0]
        val requestCommand = commands[command] ?: throw IllegalArgumentException("Unrecognised command '$command'.")
        return requestCommand.handle(ipAddress, port, args, settings, playbackState)
    }

    private fun respond(serverSocket: DatagramSocket, receivePacket: DatagramPacket, data: ByteArray) {
        val ipAddress = receivePacket.address
        val sendPacket = DatagramPacket(data, data.size, ipAddress, receivePacket.port)

        serverSocket.send(sendPacket)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(RequestHandlerImpl::class.java)
    }
}
