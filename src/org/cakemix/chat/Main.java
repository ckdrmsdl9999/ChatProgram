/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakemix.chat;

import com.esotericsoftware.minlog.Log;
import java.io.IOException;

/**
 *
 * @author cakemix
 */
public class Main {

    public static void main(String[] args) {
        Log.set(Log.LEVEL_DEBUG);
        if (args.length > 0) {
            if (args[0].equals("Server")) {
                try {
                    new ChatServer();
                    new ChatFrameRedux();
                } catch (IOException e) {
                }
            } else if (args[0].equals("ServerOnly")) {
                try {
                    new ChatServer();
                } catch (IOException e) {
                }
            } else {
                new ChatFrameRedux();
            }
        } else {
            new ChatFrameRedux();
        }
    }
}
