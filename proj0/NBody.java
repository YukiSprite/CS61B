public class NBody{
    public static double radius;
    public static int num;
    public static double readRadius(String args){
        In in = new In(args);
        int num = in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String args){
        In in = new In(args);
        num = in.readInt();
        Planet[] planets = new Planet[num];
        radius = in.readDouble();
        for(int i=0;i<num;i++){
            double xp = in.readDouble();
            double xv = in.readDouble();
            double yp = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xp,xv,yp,yv,m,img);
        }
        return planets;
    }
    public static void main(String[] args){
        double T =  Double.parseDouble(args[0]);
        double dt =  Double.parseDouble(args[1]);
        String filename = args[2];
        Planet [] planets = readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        StdDraw.show();

        for(double time = 0;time < T;time+=dt){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i=0;i<num;i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
//            StdDraw.clear();
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}