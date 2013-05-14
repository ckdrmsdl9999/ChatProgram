package org.cakemix.chat;

import com.esotericsoftware.kryonet.Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;
import org.cakemix.*;

public class ChatFrameRedux extends JFrame implements ActionListener {

    //Swing controls
    JScrollPane messagePane;
    JTextPane messageList;
    JScrollPane userPane;
    JList userList;
    JTextField sendText;
    JButton sendButton, newConnection, toggleList;
    JMenuBar menu;
    JMenu file, view;
    StyledDocument doc;
    // Kryo Client
    ChatClientRedux chatClient;

    public ChatFrameRedux() {
        super("Chat Client");
        // set default close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        buildMenu();

        Container contentPane = getContentPane();
        buildUI(contentPane);
        setJMenuBar(menu);
        //super pack cus we want the window to fit the gubbins
        super.pack();

        // when enter is pressed in the send text box
        // send the text by doing the send button click
        sendText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick();
            }
        });

        // Setup the Network Client
        //chatClient = new ChatClientRedux(Network.getConnectionDetails(), this);
        chatClient = new ChatClientRedux();
        setVisible(true);
    }

    /*
     * override for pack
     * does not resize the window ;)
     */
    @Override
    public void pack() {
        Dimension newSize = getSize();
        super.pack();
        setSize(newSize);
    }

    //<editor-fold desc="form setup" defaultstate="collapsed">
    private void buildMenu() {

        menu = new JMenuBar();

        file = new JMenu("File");
        JMenuItem menuI = new JMenuItem("Connect");

        menuI.addActionListener(this);
        file.add(menuI);
        menuI = new JMenuItem("Exit");
        menuI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        file.add(menuI);

        menu.add(file);

        view = new JMenu("View");
        menuI = new JMenuItem("Hide Userlist");
        menuI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                toggleList.doClick();
            }
        });

        view.add(menuI);

        menu.add(view);
    }

    private void buildUI(Container contentPane) {

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // tell the panel to use the layout
        contentPane.setLayout(layout);

        // create the panes
        // messages
        messagePane = new JScrollPane(
                messageList = new JTextPane());
        doc= messageList.getStyledDocument();

        // user list
        userPane = new JScrollPane(userList = new JList());

        //set list porperties
        userList.setModel(new DefaultListModel());
//        DefaultListSelectionModel disableSelections = new DefaultListSelectionModel() {
//            public void setSelectionInterval(int index0, int index1) {
//            }
//        };
//        userList.setSelectionModel(disableSelections);

        // create the funky buttons
        newConnection = new JButton("New Connection");
        newConnection.addActionListener(this);

        //set action fr toggle list
        toggleList = new JButton("Hide Userlist");
        //set action for toggleList
        toggleList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (userPane.isVisible()) {
                    userPane.setVisible(false);
                    pack();
                } else {
                    userPane.setVisible(true);
                    pack();
                }
            }
        });

        //create input controls
        sendText = new JTextField();
        sendButton = new JButton("Send");
        // This listener is called when the send button is clicked.
        setSendListener(new Runnable() {
            public void run() {
                Network.ChatMessage chatMessage = new Network.ChatMessage();
                chatMessage.text = getSendText();
                chatClient.getClient().sendTCP(chatMessage);
            }
        });

        // create the group
        layout.setHonorsVisibility(true);
        layout.setHorizontalGroup(layout.createParallelGroup()
                //buttons at top (change this for a menu)
                .addGroup(layout.createSequentialGroup()
                .addComponent(newConnection)
                .addComponent(toggleList))
                //main focus (left list && right chat)
                .addGroup(layout.createSequentialGroup()
                .addComponent(userPane, 64, 96, 128)
                .addComponent(messagePane, 64, 320, Short.MAX_VALUE))
                //input at the bottom
                .addGroup(layout.createSequentialGroup()
                .addComponent(sendText)
                .addComponent(sendButton)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                //buttons at top (change this for a menu)
                .addGroup(layout.createParallelGroup()
                .addComponent(newConnection)
                .addComponent(toggleList))
                //main focus (left list && right chat)
                .addGroup(layout.createParallelGroup()
                .addComponent(messagePane)
                .addComponent(userPane))
                //input at the bottom
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                .addComponent(sendText)
                .addComponent(sendButton)));


    }
    //</editor-fold>

    protected void createNewConnection(final ChatFrameRedux frame) {
        newConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current client
                chatClient.dispose();
                //setup a new one
                chatClient = new ChatClientRedux(Network.getConnectionDetails(), frame);
            }
        });
    }

    protected void setChatListeners() {



        // This listener is called when the chat window is closed.
        setCloseListener(new Runnable() {
            public void run() {
                chatClient.getClient().stop();
            }
        });
    }

    /*
     * Were all the fun happens
     * thanks to figuring this out, i can move the clumsy looking buttons
     * into classy looking menus!
     *
     * then work out how to get not clumsy looking buttons too...
     * and toolbars...
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Connection") ||
            e.getActionCommand().equals("Connect")){
            // Dispose of the current client
            chatClient.dispose();
            //setup a new one
            chatClient = new ChatClientRedux(Network.getConnectionDetails(), this);
        }
        if (e.getActionCommand().equals("Hide Userlist")){
            if (userPane.isVisible()) {
                    userPane.setVisible(false);
                    //e.getSource() Use this to get the caller
                    // check if its a button or menu item, then update
                    // use it to change from "show >> hide >> show"
                    // or add a tick in the menu
                    pack();
                } else {
                    userPane.setVisible(true);
                    pack();
                }
        }
    }

    public void setSendListener(final Runnable listener) {
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (getSendText().length() == 0) {
                    return;
                }
                listener.run();
                sendText.setText("");
                sendText.requestFocus();
            }
        });
    }

    public void setCloseListener(final Runnable listener) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent evt) {
                listener.run();
            }

            @Override
            public void windowActivated(WindowEvent evt) {
                sendText.requestFocus();
            }
        });
    }

    public String getSendText() {
        return sendText.getText().trim();
    }

    public void setNames(final String[] names) {
        // This listener is run on the client's update thread,
        // which was started by client.start().
        // We must be careful to only interact with Swing
        // components on the Swing event thread.
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DefaultListModel model = (DefaultListModel) userList.getModel();
                model.removeAllElements();
                for (String name : names) {
                    model.addElement(name);
                }
            }
        });
    }

    public void addMessage(final String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {


                try {
                    if (doc.getLength() > 0) {
                        doc.insertString(doc.getLength(), '\n' + message, null);
                    } else {
                        doc.insertString(doc.getLength(), message, null);
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                messageList.setDocument(doc);
            }
        });
    }
}