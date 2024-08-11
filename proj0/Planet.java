import java.awt.*;

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    double G = 6.67e-11;
    public Planet( double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        if(p==this) return 0;
        return G*mass*p.mass/Math.pow(calcDistance(p),2);
    }
    public double calcForceExertedByX(Planet p){
        if(p==this) return 0;
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        if(p==this) return 0;
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double totalForceX = 0;
        for(Planet p : allPlanets){
            if(p==this) continue;
            totalForceX += calcForceExertedByX(p);
        }
        return totalForceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double totalForceY = 0;
        for(Planet P:allPlanets){
            if(P==this) continue;
            totalForceY += calcForceExertedByY(P);
        }
        return totalForceY;
    }
    public void update(double dt, double fX, double fY){
        double xxAcc = fX/mass;
        double yyAcc = fY/mass;
        xxVel += dt * xxAcc;
        yyVel += dt * yyAcc;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}