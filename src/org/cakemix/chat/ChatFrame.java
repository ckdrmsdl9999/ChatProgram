package org.cakemix.chat;

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

public class ChatFrame extends JFrame {

        CardLayout cardLayout;
        JProgressBar progressBar;
        JTextPane messageList;
        JTextField sendText;
        JButton sendButton, newConnection, toggleList;
        JList nameList;

        public JMenuBar menu;
        JMenu file, view;
        ActionListener newConn;


        public ChatFrame(String host) {
            super("Chat Client");
            // set default close
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            buildMenu();

            Container contentPane = getContentPane();
            cardLayout = new CardLayout();
            contentPane.setLayout(cardLayout);
            {
                JPanel panel = new JPanel(new BorderLayout());
                contentPane.add(panel, "progress");
                panel.add(new JLabel("Connecting to " + host + "..."));
                {
                    panel.add(progressBar = new JProgressBar(), BorderLayout.SOUTH);
                    progressBar.setIndeterminate(true);
                }
            }
            {
                buildUI(contentPane);
                setJMenuBar(menu);
                //super pack cus we want the window to fit the gubbins
                super.pack();
            }

            // when enter is pressed in the send text box
            // send the text by doing the send button click
            sendText.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendButton.doClick();
                }
            });
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

        private void buildMenu(){

            menu= new JMenuBar();

            file = new JMenu("File");
            JMenuItem menuI = new JMenuItem("Connect");

            menuI.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    newConnection.doClick();
                }
            });
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
            //create a panel to chuck everything on
            JPanel panel = new JPanel(new BorderLayout());
            //add panel to the main frame
            contentPane.add(panel, "chat");
            {
                // create the panel to hold the top componants
                JPanel topPanel = new JPanel();
                // create the layout for the panel
                GroupLayout layout = new GroupLayout(topPanel);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);
                // tell the panel to use the layout
                topPanel.setLayout(layout);

                // create the panes
                // messages
                JScrollPane messagePane = new JScrollPane(
                        messageList = new JTextPane());

                // user list
                final JScrollPane usersPane = new JScrollPane(nameList = new JList());

                //set list porperties
                nameList.setModel(new DefaultListModel());
                DefaultListSelectionModel disableSelections = new DefaultListSelectionModel() {
                    public void setSelectionInterval(int index0, int index1) {
                    }
                };
                nameList.setSelectionModel(disableSelections);

                // create the funky buttons
                newConnection = new JButton("New Connection");
                toggleList = new JButton("Hide Userlist");
                //set action for toggleList
                toggleList.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (usersPane.isVisible()) {
                            usersPane.setVisible(false);
                            pack();
                        } else {
                            usersPane.setVisible(true);
                            pack();
                        }
                    }
                });

                //create input controls
                sendText = new JTextField();
                sendButton = new JButton("Send");

                // create the group
                layout.setHonorsVisibility(true);
                layout.setHorizontalGroup(layout.createParallelGroup()
                        //buttons at top (change this for a menu)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(newConnection)
                        .addComponent(toggleList))
                        //main focus (left list && right chat)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(usersPane, 64, 96, 128)
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
                        .addComponent(usersPane))
                        //input at the bottom
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(sendText)
                        .addComponent(sendButton)));


                // add the panel to the main panel
                panel.add(topPanel);
            }

        }

        protected void createNewConnection(final Runnable listener) {
            newConnection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listener.run();
                }
            });
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
                    cardLayout.show(getContentPane(), "chat");
                    DefaultListModel model = (DefaultListModel) nameList.getModel();
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

                    Document doc = messageList.getDocument();
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