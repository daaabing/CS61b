public class Planet {
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif)

    private static final double gravitation = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b){
        double dis = (xxPos - b.xxPos)*(xxPos - b.xxPos) + (yyPos - b.yyPos)*(yyPos - b.yyPos);
        return Math.sqrt(dis);
    }

    public double calcForceExertedBy(Planet b){
        return (gravitation*mass*b.mass)/(calcDistance(b)*calcDistance(b));
    }

    public double calcForceExertedByX(Planet b){
        return calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY(Planet b){
        return calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet [] a){
        double force = 0;
        for (Planet planet : a) {
            if (this.equals(planet)){
                continue;
            }
            force = force + calcForceExertedByX(planet);
        }
        return force;
    }

    public double calcNetForceExertedByY(Planet [] b){
        double force = 0;
        for (Planet planet : b) {
            if (this.equals(planet)){
                continue;
            }
            force = force + calcForceExertedByY(planet);
        }
        return force;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX/mass;
        double aY = fY/mass;
        xxVel = xxVel + dt*aX;
        yyVel = yyVel + dt*aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
