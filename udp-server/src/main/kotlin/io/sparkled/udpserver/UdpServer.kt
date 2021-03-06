package io.sparkled.udpserver

import java.net.DatagramSocket

/**
 * A UDP Server that listens on the given port.
 */
interface UdpServer {

    /**
     * Starts the UDP server. This method is idempotent.
     * @param socket The socket to listen on.
     */
    fun start(socket: DatagramSocket)

    /**
     * Shuts the UDP server down.
     */
    fun stop()
}
