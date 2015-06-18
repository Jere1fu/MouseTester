package mousetester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Tjen
 */
public class MouseTester extends JApplet
{
    public static void main(String[] args)
    {
        JFrame frame;
        frame = new JFrame("MouseTesterApplication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new MouseTesterPanel());
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void init()
    {
        super.init();
        setName("MouseTesterApplet");
        setContentPane(new MouseTesterPanel());
        setSize(500, 500);
    }
    
    private static class MouseTesterPanel extends JPanel implements MouseInputListener
    {
        private static final int MAX_SIZE = 10;
        private JPanel mousePanel;
        private LinkedList<MouseInput> inputs = new LinkedList<>();
        private JTextArea output;

        private enum MouseInput
        {
            mouseClicked, mousePressed, mouseReleased, mouseEntered, mouseExited, mouseDragged, mouseMoved
        }

        @SuppressWarnings("LeakingThisInConstructor")
        private MouseTesterPanel()
        {
            setLayout(new BorderLayout());
            mousePanel = new JPanel();
            mousePanel.setPreferredSize(new Dimension(500, 500));
            mousePanel.addMouseListener(this);
            mousePanel.addMouseMotionListener(this);
            mousePanel.setBackground(Color.GRAY);
            add(mousePanel, BorderLayout.CENTER);

            output = new JTextArea(MAX_SIZE, 50);
            output.setEditable(false);
            add(output, BorderLayout.SOUTH);

            setVisible(true);
        }

        private void print()
        {
            System.out.println();
            StringBuilder b = new StringBuilder();
            boolean first = true;
            for (MouseInput mouseInput : inputs)
            {
                if (first)
                {
                    first = false;
                } else
                {
                    b.append("\n");
                }
                b.append(mouseInput);
            }
            output.setText(b.toString());
            output.setCaretPosition(output.getText().length());
        }

        private void addLast(MouseInput input)
        {
            while (inputs.size() >= MAX_SIZE)
            {
                inputs.removeFirst();
            }
            inputs.add(input);
        }

        private MouseInput getLast()
        {
            if (inputs.isEmpty())
            {
                return null;
            }
            return inputs.getLast();
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (getLast() != MouseInput.mouseClicked)
            {
                addLast(MouseInput.mouseClicked);
                print();
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            if (getLast() != MouseInput.mousePressed)
            {
                addLast(MouseInput.mousePressed);
                print();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (getLast() != MouseInput.mouseReleased)
            {
                addLast(MouseInput.mouseReleased);
                print();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            if (getLast() != MouseInput.mouseEntered)
            {
                addLast(MouseInput.mouseEntered);
                print();
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            if (getLast() != MouseInput.mouseExited)
            {
                addLast(MouseInput.mouseExited);
                print();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (getLast() != MouseInput.mouseDragged)
            {
                addLast(MouseInput.mouseDragged);
                print();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            if (getLast() != MouseInput.mouseMoved)
            {
                addLast(MouseInput.mouseMoved);
                print();
            }
        }
    }
}
