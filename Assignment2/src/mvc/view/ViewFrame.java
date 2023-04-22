package mvc.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewFrame extends JFrame{
    private JPanel contentPane;
    private JTextField timeTextField;
    private JTextField nrClientsTextField;
    private JTextField nrQueuesTextField;
    private JTextField minArrivalTextField;
    private JTextField maxArrivalTextField;
    private JTextField minServiceTextField;
    private JTextField maxServiceTextField;
    private JTextField maxInQTextField;
    private JTextArea outputTextArea;
    private JButton simulateButton;
    public ViewFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1010, 626);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Queue simulation");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titleLabel.setBounds(327, 20, 335, 45);
        contentPane.add(titleLabel);

        simulateButton = new JButton("Simulate");
        simulateButton.setFont(new Font("Tahoma", Font.PLAIN, 44));
        simulateButton.setBounds(358, 482, 365, 65);
        contentPane.add(simulateButton);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        timeLabel.setBounds(55, 98, 110, 31);
        contentPane.add(timeLabel);

        JLabel lblNrClients = new JLabel("Nr Clients");
        lblNrClients.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblNrClients.setBounds(55, 138, 168, 25);
        contentPane.add(lblNrClients);

        JLabel lblNrQueues = new JLabel("Nr queues:");
        lblNrQueues.setFont(new Font("Tahoma", Font.PLAIN, 27));
        lblNrQueues.setBounds(55, 173, 137, 31);
        contentPane.add(lblNrQueues);

        JLabel lblMinarrival = new JLabel("MinArrival:");
        lblMinarrival.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblMinarrival.setBounds(55, 214, 168, 31);
        contentPane.add(lblMinarrival);

        JLabel lblMaxarrival = new JLabel("MaxArrival:");
        lblMaxarrival.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblMaxarrival.setBounds(55, 264, 168, 31);
        contentPane.add(lblMaxarrival);

        JLabel lblMinserivce = new JLabel("MinSerivce:");
        lblMinserivce.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblMinserivce.setBounds(55, 305, 168, 31);
        contentPane.add(lblMinserivce);

        JLabel lblMaxservice = new JLabel("MaxService:");
        lblMaxservice.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblMaxservice.setBounds(55, 357, 168, 31);
        contentPane.add(lblMaxservice);

        JLabel lblMaxnrinaqueue = new JLabel("MaxNrInAQueue");
        lblMaxnrinaqueue.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblMaxnrinaqueue.setBounds(24, 412, 231, 31);
        contentPane.add(lblMaxnrinaqueue);

        timeTextField = new JTextField();
        timeTextField.setBounds(187, 98, 174, 33);
        contentPane.add(timeTextField);
        timeTextField.setColumns(10);

        nrClientsTextField = new JTextField();
        nrClientsTextField.setColumns(10);
        nrClientsTextField.setBounds(197, 144, 174, 32);
        contentPane.add(nrClientsTextField);

        nrQueuesTextField = new JTextField();
        nrQueuesTextField.setColumns(10);
        nrQueuesTextField.setBounds(202, 186, 192, 30);
        contentPane.add(nrQueuesTextField);

        minArrivalTextField = new JTextField();
        minArrivalTextField.setColumns(10);
        minArrivalTextField.setBounds(202, 226, 192, 28);
        contentPane.add(minArrivalTextField);

        maxArrivalTextField = new JTextField();
        maxArrivalTextField.setColumns(10);
        maxArrivalTextField.setBounds(220, 276, 184, 33);
        contentPane.add(maxArrivalTextField);

        minServiceTextField = new JTextField();
        minServiceTextField.setColumns(10);
        minServiceTextField.setBounds(220, 319, 192, 28);
        contentPane.add(minServiceTextField);

        maxServiceTextField = new JTextField();
        maxServiceTextField.setColumns(10);
        maxServiceTextField.setBounds(230, 369, 174, 33);
        contentPane.add(maxServiceTextField);

        maxInQTextField = new JTextField();
        maxInQTextField.setColumns(10);
        maxInQTextField.setBounds(257, 426, 174, 31);
        contentPane.add(maxInQTextField);

        outputTextArea = new JTextArea();
        outputTextArea.setBounds(535, 112, 431, 313);
        contentPane.add(outputTextArea);
        outputTextArea.setColumns(10);

        this.setVisible(true);
    }

    public void addSimulateListener(ActionListener action) {
        this.simulateButton.addActionListener(action);
    }

    public int getTimeTextField() {
        return Integer.parseInt(timeTextField.getText());
    }

    public void setTimeTextField(JTextField timeTextField) {
        this.timeTextField = timeTextField;
    }

    public int getNrClientsTextField() {
        return Integer.parseInt(nrClientsTextField.getText());
    }

    public void setNrClientsTextField(JTextField nrClientsTextField) {
        this.nrClientsTextField = nrClientsTextField;
    }

    public int getNrQueuesTextField() {
        return Integer.parseInt(nrQueuesTextField.getText());
    }

    public void setNrQueuesTextField(JTextField nrQueuesTextField) {
        this.nrQueuesTextField = nrQueuesTextField;
    }

    public int getMinArrivalTextField() {
        return Integer.parseInt(minArrivalTextField.getText());
    }

    public void setMinArrivalTextField(JTextField minArrivalTextField) {
        this.minArrivalTextField = minArrivalTextField;
    }

    public int getMaxArrivalTextField() {
        return Integer.parseInt(maxArrivalTextField.getText());
    }

    public void setMaxArrivalTextField(JTextField maxArrivalTextField) {
        this.maxArrivalTextField = maxArrivalTextField;
    }

    public int getMinServiceTextField() {
        return Integer.parseInt(minServiceTextField.getText());
    }

    public void setMinServiceTextField(JTextField minServiceTextField) {
        this.minServiceTextField = minServiceTextField;
    }

    public int getMaxServiceTextField() {
        return Integer.parseInt(maxServiceTextField.getText());
    }

    public void setMaxServiceTextField(JTextField maxServiceTextField) {
        this.maxServiceTextField = maxServiceTextField;
    }

    public int getMaxInQTextField() {
        return Integer.parseInt(maxInQTextField.getText());
    }

    public void setMaxInQTextField(JTextField maxInQTextField) {
        this.maxInQTextField = maxInQTextField;
    }

    public JTextArea getOutputTextArea() {
        return outputTextArea;
    }

    public void setOutputTextArea(String outputTextArea) {
        this.outputTextArea.setText(outputTextArea);
    }

    public JButton getSimulateButton() {
        return simulateButton;
    }

    public void setSimulateButton(JButton simulateButton) {
        this.simulateButton = simulateButton;
    }
}
