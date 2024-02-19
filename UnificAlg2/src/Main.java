import javax.swing.JOptionPane;
import java.awt.Cursor;

public class Main extends javax.swing.JFrame {

    AlgRecLinhas algRecLinhas;
    AlgRecPoli algRecPoli;
    AlgCurvaBezier algCurvaBezier;
    AlgCurvaSpline algCurvaSpline;
    AlgTransfTransl algTransfTransl;
    AlgTransfEscala algTransfEscala;
    AlgTransfRot algTransfRot;

    int valor;

    public Main() {
        initComponents();
    }
    private void initComponents() {
        menuBar = new javax.swing.JMenuBar();
        menuRecortes = new javax.swing.JMenu();
        linhasRetas = new javax.swing.JMenuItem();
        linhasPoli = new javax.swing.JMenuItem();
        menuCurvas = new javax.swing.JMenu();
        curvasBezier = new javax.swing.JMenuItem();
        curvasSpilne = new javax.swing.JMenuItem();
        menuTransf = new javax.swing.JMenu();
        transfTransl = new javax.swing.JMenuItem();
        transfEscala = new javax.swing.JMenuItem();
        transfRot = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Paint 3");
        menuRecortes.setText("Recortes");
        linhasRetas.setText("Linhas Retas");
        linhasRetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linhasRetasActionPerformed(evt);
            }
        });
        menuRecortes.add(linhasRetas);
        linhasPoli.setText("Polígonos");
        linhasPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linhasPoliActionPerformed(evt);
            }
        });
        menuRecortes.add(linhasPoli);
        menuBar.add(menuRecortes);
        menuCurvas.setText("Curvas");
        curvasBezier.setText("Bezier");
        curvasBezier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curvasBezierActionPerformed(evt);
            }
        });
        menuCurvas.add(curvasBezier);
        curvasSpilne.setText("Spilne");
        curvasSpilne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curvasSpilneActionPerformed(evt);
            }
        });
        menuCurvas.add(curvasSpilne);
        menuBar.add(menuCurvas);
        menuTransf.setText("Transformações");
        transfTransl.setText("Translação");
        transfTransl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transfTranslActionPerformed(evt);
            }
        });
        menuTransf.add(transfTransl);
        transfEscala.setText("Escala");
        transfEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transfEscalaActionPerformed(evt);
            }
        });
        menuTransf.add(transfEscala);
        transfRot.setText("Rotação");
        transfRot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transfRotActionPerformed(evt);
            }
        });
        menuTransf.add(transfRot);
        menuBar.add(menuTransf);
        setJMenuBar(menuBar);
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 497, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 288, Short.MAX_VALUE));
        pack();
    }

    private void linhasRetasActionPerformed(java.awt.event.ActionEvent evt) {
        algRecLinhas = new AlgRecLinhas();
    }

    private void linhasPoliActionPerformed(java.awt.event.ActionEvent evt) {
        algRecPoli = new AlgRecPoli();
    }

    private void curvasBezierActionPerformed(java.awt.event.ActionEvent evt) {
        valor = Integer.parseInt(JOptionPane.showInputDialog("Número de pontos:"));
        algCurvaBezier = new AlgCurvaBezier(valor);
    }

    private void curvasSpilneActionPerformed(java.awt.event.ActionEvent evt) {
        valor = Integer.parseInt(JOptionPane.showInputDialog("Número de pontos:"));
        algCurvaSpline = new AlgCurvaSpline(valor);
    }

    private void transfTranslActionPerformed(java.awt.event.ActionEvent evt) {
        algTransfTransl = new AlgTransfTransl();
    }

    private void transfEscalaActionPerformed(java.awt.event.ActionEvent evt) {
        algTransfEscala = new AlgTransfEscala();
    }

    private void transfRotActionPerformed(java.awt.event.ActionEvent evt) {
        algTransfRot = new AlgTransfRot();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private javax.swing.JMenuItem curvasBezier;
    private javax.swing.JMenuItem curvasSpilne;
    private javax.swing.JMenuItem linhasPoli;
    private javax.swing.JMenuItem linhasRetas;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCurvas;
    private javax.swing.JMenu menuRecortes;
    private javax.swing.JMenu menuTransf;
    private javax.swing.JMenuItem transfEscala;
    private javax.swing.JMenuItem transfRot;
    private javax.swing.JMenuItem transfTransl;
}
