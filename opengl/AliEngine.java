/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opengl;

import view.frmMain;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.InputStream;
import com.jogamp.opengl.GL;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.KeyEvent;
import java.util.Random;


/**
 *
 * @author 818222
 */
public class AliEngine extends GLJPanel implements GLEventListener, java.awt.event.KeyListener {
    
    
    private FPSAnimator animator;
    private Texture tex;
    private Texture tex2;
    private Texture tex3;
    private Texture tex4;
    private Texture tex5;
    private Texture back;
    private Texture p1;
    private Texture p2;
    private Texture p3;
    private Texture p4;
    private Texture p5;
    private Texture p6;
    private Texture pressany;
    private Texture shot;
    
    private GLU glu;
    private int nave=0;    
    private int tela=0;
    private int pred1=0;
    private int pred2=0;
    private int pred3=0;
    private int espera=-1;
    private int espera2=0;
    private int score=0;
    private int hotbullet=0;
    private int esperabullet=0;    
    private int tiropatodolado=0;
    private float mvtiro=-4.90f;
    private float generatedFloat;
    private float generatedFloat2;
    private float generatedFloat3;
    private float mv;
    private float mvp2;
    private float mvp3;
    
    private float move = -1.0f;
    private float gotchamove = -1.0f;
    private float speed = 0.30f;
    
    
     public AliEngine() {
          super(new GLCapabilities(GLProfile.getDefault()));
        this.addGLEventListener(this);
        this.addKeyListener(this);        
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        glu = new GLU();
        animator = new FPSAnimator(this, 15);
        animator.start();
        
        gl.glClearColor(0f, 0f, 0f, 1f);
     
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
        gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
       
        //carregar textura
        tex = carregarTextura("/texturas/nave_1.png");
        tex2 = carregarTextura("/texturas/nave_2.png");
        tex3 = carregarTextura("/texturas/nave_3.png");
        tex4 = carregarTextura("/texturas/nave_4.png");
        tex5 = carregarTextura("/texturas/nave_5.png");
        back = carregarTextura("/texturas/back.png");
        p1 = carregarTextura("/texturas/p1.png");
        p2 = carregarTextura("/texturas/p2.png");
        p3 = carregarTextura("/texturas/p3.png");
        p4 = carregarTextura("/texturas/p4.png");
        p5 = carregarTextura("/texturas/p5.png");
        p6 = carregarTextura("/texturas/p6.png");
        pressany = carregarTextura("/texturas/1.png");
        shot = carregarTextura("/texturas/shot.png");
        
        float leftLimit = -6.5f;
       float rightLimit = 4.5f;
       generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
       generatedFloat2 = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
       generatedFloat3 = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
       mv = 6.0f;
       mvp2 = 6.0f;
       mvp3 = 6.0f;
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();

        if (animator != null) {
            if (animator.isStarted()) {
                animator.stop();
            }
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
       GL2 gl = drawable.getGL().getGL2();   
       
          if (esperabullet>148){
              hotbullet++;
              esperabullet=0;
          }
          if (tela==1)
          esperabullet++;
          frmMain.ammo.setText(Integer.toString(hotbullet));
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();       
     
          gl.glTranslatef(0.0f, 0.0f, -10.0f);

        if (tela==0){
            espera2++;
            pressany.enable(gl);
            pressany.bind(gl);
       gl.glPushMatrix(); 
        
        gl.glBegin(GL2.GL_QUADS);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-8.5f, -6.75f, -6f);      
        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(8.5f, -6.75f, -6f);        
        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(8.5f, 6.75f, -6f);    
        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-8.5f, 6.75f, -6f);       
        
        gl.glEnd();
        gl.glPopMatrix();
        pressany.disable(gl);
            
        }else{
          
      
          speed=speed+0.0005f;
       frmMain.pts.setText(Integer.toString(score));
       if (espera<30)
       espera++; 
       score++;
       back.enable(gl);
       back.bind(gl);
       gl.glPushMatrix(); 
        
        gl.glBegin(GL2.GL_QUADS);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-8.5f, -6.75f, -6f);      
        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(8.5f, -6.75f, -6f);        
        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(8.5f, 6.75f, -6f);    
        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-8.5f, 6.75f, -6f);       
        
        gl.glEnd();
        gl.glPopMatrix();
        back.disable(gl);
        
        //Tratamento de colisão Nave
        if (mv<-6.0f && generatedFloat<=move+2 && generatedFloat>=move)
            morreuCusao();
        else
            if (mv<-6.0f && generatedFloat+2>=move && generatedFloat+2<=move+2)
                morreuCusao();
            else
        if (mvp2<-6.0f && generatedFloat2<=move+2 && generatedFloat2>=move)
            morreuCusao();
        else
            if (mvp2<-6.0f && generatedFloat2+2>=move && generatedFloat2+2<=move+2)
                morreuCusao();
            else
        if (mvp3<-6.0f && generatedFloat3<=move+2 && generatedFloat3>=move)
           morreuCusao();
        else
            if (mvp3<-6.0f && generatedFloat3+2>=move && generatedFloat3+2<=move+2)
                morreuCusao();
        
        //Tratamento de colisão Tiro
          //bala mesmo X do mv/mvp
         if (mvtiro>-4.90f){
          if (mvtiro+2>mv && gotchamove+1.50f<=generatedFloat+2 && gotchamove+1.50f>=generatedFloat)
                matouCusao(1);
            else
              if (mvtiro+2>mv && gotchamove+0.50f<=generatedFloat+2 && gotchamove+0.50f>=generatedFloat)
                matouCusao(1);
            else
                  
                  
                  if (mvtiro+2>mvp2 && gotchamove+1.50f<=generatedFloat2+2 && gotchamove+1.50f>=generatedFloat2)
                matouCusao(2);
            else
              if (mvtiro+2>mvp2 && gotchamove+0.50f<=generatedFloat2+2 && gotchamove+0.50f>=generatedFloat2)
                matouCusao(2);
            else
                  
                  
                  if (mvtiro+2>mvp3 && gotchamove+1.50f<=generatedFloat3+2 && gotchamove+1.50f>=generatedFloat3)
                matouCusao(3);
            else
              if (mvtiro+2>mvp3 && gotchamove+0.50f<=generatedFloat3+2 && gotchamove+0.50f>=generatedFloat3)
                matouCusao(3);  
         }
            
        
       if (nave == 0){
                tex.enable(gl);
                tex.bind(gl);
                nave++;
        }else
        if (nave == 1){
                tex2.enable(gl);
                tex2.bind(gl);
                nave++;
        }else
        if (nave == 2){
                tex3.enable(gl);
                tex3.bind(gl);
                nave++;
        }else
        if (nave == 3){
                tex4.enable(gl);
                tex4.bind(gl);
                nave++;
        }else
        if (nave == 4){
                tex5.enable(gl);
                tex5.bind(gl);
                nave++;
        }else
        if (nave == 5){
                tex.enable(gl);
                tex.bind(gl);                
                nave=0;
        } 
        gl.glPushMatrix();  
        
        gl.glBegin(GL2.GL_QUADS);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-7.0f, move, -6f);        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-5.0f, move, -6f);        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-5.0f, move+2, -6f);        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-7.0f, move+2, -6f);   
        
        
        gl.glEnd();
        gl.glPopMatrix();      
        tex.disable(gl);
        tex2.disable(gl);
        tex3.disable(gl);
        tex4.disable(gl);
        tex5.disable(gl);
        
        if (pred1 == 0){
                p1.enable(gl);
                p1.bind(gl);
                pred1++;
        }else
        if (pred1 == 1){
                p2.enable(gl);
                p2.bind(gl);
                pred1++;
        }else
        if (pred1 == 2){
                p3.enable(gl);
                p3.bind(gl);
                pred1++;
        }else
        if (pred1 == 3){
                p4.enable(gl);
                p4.bind(gl);
                pred1++;
        }else
        if (pred1 == 4){
                p5.enable(gl);
                p5.bind(gl);
                pred1++;
        }else
        if (pred1 == 5){
                p6.enable(gl);
                p6.bind(gl);                
                pred1++;
        }else
        if (pred1 == 6){
                p1.enable(gl);
                p1.bind(gl);                
                pred1=0;
        }
        
              
        
                
        float mv1 = generatedFloat;
        gl.glPushMatrix();  
        
        gl.glBegin(GL2.GL_QUADS);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(mv, mv1, -6f);   
        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(mv+2, mv1, -6f);    
        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(mv+2, mv1+2, -6f);    
        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(mv, mv1+2, -6f);
        
        gl.glEnd();
        gl.glPopMatrix();
        
        if (mv>-9.0f)
        mv=mv-speed;
        else{
        mv=6.0f;
         float leftLimit = -6.5f;
       float rightLimit = 4.5f;
       generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);        
        }      
        
        p1.disable(gl);
        p2.disable(gl);
        p3.disable(gl);
        p4.disable(gl);
        p5.disable(gl); 
        p6.disable(gl); 
        
        
        if (espera>14){
        if (pred2 == 0){
                p1.enable(gl);
                p1.bind(gl);
                pred2++;
        }else
        if (pred2 == 1){
                p2.enable(gl);
                p2.bind(gl);
                pred2++;
        }else
        if (pred2 == 2){
                p3.enable(gl);
                p3.bind(gl);
                pred2++;
        }else
        if (pred2 == 3){
                p4.enable(gl);
                p4.bind(gl);
                pred2++;
        }else
        if (pred2 == 4){
                p5.enable(gl);
                p5.bind(gl);
                pred2++;
        }else
        if (pred2 == 5){
                p6.enable(gl);
                p6.bind(gl);                
                pred2++;
        }else
        if (pred2 == 6){
                p1.enable(gl);
                p1.bind(gl);                
                pred2=0;
        }
        
              
        
                
        float mv2 = generatedFloat2;
        gl.glPushMatrix();  
        
        gl.glBegin(GL2.GL_QUADS);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(mvp2, mv2, -6f);   
        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(mvp2+2, mv2, -6f);    
        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(mvp2+2, mv2+2, -6f);    
        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(mvp2, mv2+2, -6f);
        
        gl.glEnd();
        gl.glPopMatrix();
        
        if (mvp2>-9.0f)
        mvp2=mvp2-speed;
        else{
        mvp2=6.0f;
         float leftLimit = -6.5f;
       float rightLimit = 4.5f;
       generatedFloat2 = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);        
        }      
        
        p1.disable(gl);
        p2.disable(gl);
        p3.disable(gl);
        p4.disable(gl);
        p5.disable(gl); 
        p6.disable(gl);
        }
        
        if (espera>29){
        if (pred3 == 0){
                p1.enable(gl);
                p1.bind(gl);
                pred3++;
        }else
        if (pred3 == 1){
                p2.enable(gl);
                p2.bind(gl);
                pred3++;
        }else
        if (pred3 == 2){
                p3.enable(gl);
                p3.bind(gl);
                pred3++;
        }else
        if (pred3 == 3){
                p4.enable(gl);
                p4.bind(gl);
                pred3++;
        }else
        if (pred3 == 4){
                p5.enable(gl);
                p5.bind(gl);
                pred3++;
        }else
        if (pred3 == 5){
                p6.enable(gl);
                p6.bind(gl);                
                pred3++;
        }else
        if (pred3 == 6){
                p1.enable(gl);
                p1.bind(gl);                
                pred3=0;
        }
        
              
        
                
        float mv3 = generatedFloat3;
        gl.glPushMatrix();  
        
        gl.glBegin(GL2.GL_QUADS);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(mvp3, mv3, -6f);   
        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(mvp3+2, mv3, -6f);    
        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(mvp3+2, mv3+2, -6f);    
        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(mvp3, mv3+2, -6f);
        
        gl.glEnd();
        gl.glPopMatrix();
        
        if (mvp3>-9.0f)
        mvp3=mvp3-speed;
        else{
        mvp3=6.0f;
         float leftLimit = -6.5f;
       float rightLimit = 4.5f;
       generatedFloat3 = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);        
        }      
        
        p1.disable(gl);
        p2.disable(gl);
        p3.disable(gl);
        p4.disable(gl);
        p5.disable(gl); 
        p6.disable(gl);
        pressany.disable(gl);
        }
        
        if (tiropatodolado==1){
           
          shot.enable(gl);
          shot.bind(gl);
                
           gl.glPushMatrix();  
        
        gl.glBegin(GL2.GL_QUADS);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(mvtiro, gotchamove+0.50f, -6f);   
        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(mvtiro+2, gotchamove+0.50f, -6f);    
        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(mvtiro+2, gotchamove+1.50f, -6f);    
        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(mvtiro, gotchamove+1.50f, -6f);
        
        gl.glEnd();
        gl.glPopMatrix();
        
        mvtiro=mvtiro+speed;
        if (mvtiro>7.0f){
            mvtiro=-4.90f;
            tiropatodolado=0;
        }
        
        
           
     }
        
        
         shot.disable(gl);}  
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();

        if (height == 0) {
            height = 1;
        }
        float aspect = (float) width / height;

        // Configurar área de visualização para o tamanho da janela
        gl.glViewport(0, 0, width, height);

        // Definir projeção em perspectiva 
        gl.glMatrixMode(GL_PROJECTION);  // Matriz de projeção 
        gl.glLoadIdentity();             // Resetar projection matrix
        glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }
     
public Texture carregarTextura(String PATH) {
        try {
            InputStream stream = getClass().getResourceAsStream(PATH);
            TextureData data
                    = TextureIO.newTextureData(
                            GLProfile.getDefault(), stream,
                            false, "png");
            return TextureIO.newTexture(data);
        } catch (Exception e) {
            System.out.println("ERRO carregar textura: " + e.getMessage());
            return null;
        }
    } 

public void morreuCusao(){
    animator.stop();
    if (score>Integer.parseInt(frmMain.bestpts.getText()))
        frmMain.bestpts.setText(Integer.toString(score-1));
    score=0;
    espera=-1;
       mv =   9.0f;
       mvp2 = 9.0f;
       mvp3 = 9.0f;
       speed= 0.30f;
       tela=0;
       move = -1.0f;
       hotbullet=0;
       tiropatodolado=0;
       esperabullet=0;       
       animator.start();
    
}

public void matouCusao(int predatorWho){
    //colidiu com quem?
    if (predatorWho==1 && tiropatodolado==1)
        mv=-19.0f;
    if (predatorWho==2 && tiropatodolado==1)
        mvp2=-19.0f;
    if (predatorWho==3 && tiropatodolado==1)
        mvp3=-19.0f;   
    mvtiro=-4.90f;
    tiropatodolado=0;
    score=score+30;
}
   
@Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (tela==0)
            if (espera2>15){
            tela=1;
            espera2=0;
            }
       
        switch (ke.getKeyChar()) {
            case 't':
                if (tela==1)
                if (move<4.5f)
              move=move+0.20f;
                break;
            case 'g':
                if (tela==1)
                if (move>-6.5f)
                move=move-0.20f;
                break;
        }
   
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyChar()) {
            case 't':
                if (tela==1)
                if (move<4.5f)
              move=move+0.20f;
                break;
            case 'g':
                if (tela==1)
                if (move>-6.5f)
                move=move-0.20f;
                break;
            case 'b':
                if (tela==1)
                if (hotbullet>0){
                    if (mvtiro==-4.90f)
                    gotchamove=move;
                 hotbullet--;
                 tiropatodolado=1;
                }                    
                break;
        }
      
    }

}
