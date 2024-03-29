package s9.itba;

import java.util.HashSet;
import java.util.Set;

public class Storage {

	private double W;
	private double L;
	private double D;
	
	private Vector[] target;

	private Set<Particle> particles = null;

	public Storage(double W, double L, double D) {
		this.W = W;
		this.L = L;
		this.D = D;
		this.particles = new HashSet<Particle>();
		target = new Vector[2];
		target[0] = new Vector((W-D)/2,0);
		target[1] = new Vector((W+D)/2,0);
	}
	
	public void generateRandomParticle(int total){
		/*if(total < 100)
			total = 100;
		
		if(total > 300)
			total = 300;*/
		
		for(int i = 0 ; i < total ;){
			Particle p = generateRandomPos();
			if(isValidPos(p)){
				particles.add(p);
				i++;
			}
		}
	}
	
	private Particle generateRandomPos(){
		double radius = (0.5+Math.random()*0.08)/2;
		double x = Math.random()*(W-2*radius)+radius;
		double y = Math.random()*(L-2*radius)+radius;
		return new Particle(x, y, radius, Simulation.mass);
	}

	public double getD() {
		return D;
	}

	public double getL() {
		return L;
	}

	public double getW() {
		return W;
	}
	
	public Set<Particle> getParticles() {
		return particles;
	}
	
	public boolean isValidPos(Particle p){
		for(Particle p2: particles){
			if(p.getSuperposition(p2)+p.r>0)
				return false;
		}
		return true;
	}
	
	public Vector[] getTarget() {
		return target;
	}
}
