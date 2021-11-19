package JTableModule;

import Utils.SwingUtils;

import java.util.Locale;

public class JTableMain {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }
}
