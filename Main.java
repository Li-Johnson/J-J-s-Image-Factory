public class Main {
     public static void main(String[] args) {
        Window a = new Window();
        if (args.length > 0) {
            a.getImagePanel().changeImage(ImagePanel.makeImage(args[0]));
        }
        a.setVisible(true);
    }
}
