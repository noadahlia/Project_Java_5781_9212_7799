package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point3D position;


    private double kC=1, kL=0, kQ=0;

    /*
    Default constructor
     */
    public PointLight(){
        super(Color.BLACK);

    }

    /*
    Constructor with parameters
     */
    public PointLight(Color col,Point3D point, double _kC,double _kL, double _kQ){
        super(col);
        position=point;
        kC=_kC;
        kL=_kL;
        kQ=_kQ;
    }

    public PointLight(Color col,Point3D point){
        super(col);
        position=point;

    }

    /**
     * This function calculates the intensity of a points and returns the new color
     *
     * @param p
     * @return the new color with intensity
     */
    @Override
    public Color getIntensity(Point3D p) {

        double d = position.subtract(p).length(); //distance
        double tmp = kC+kL*d+kQ*(d*d);
        return (getIntensity().reduce(tmp));
    }

    /**
     * This function calculates and returns the direction of the light source
     *
     * @param p
     * @return a directional vector
     */
    @Override
    public Vector getL(Point3D p) {
        return position.subtract(p).normalize();
    }

    /*
     Getters according to Builder pattern
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }
}
