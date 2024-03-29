package s9.itba;
import java.util.Set;


public abstract class Grid {
	
	private Cell[][] cells;
	private double L;
	private int M;
	private double offset;
	
	public Grid(double L, int M, Set<Particle> particles){
		this.L = L;
		this.M = M;
		cells = new Cell[M][M];
		for(int i=0; i<M; i++){
			for(int j=0; j<M; j++){
				cells[i][j] = new Cell();
			}
		}
		offset = 0.1;
		insertParticles(particles);
		calculateNeighbours();
	}
	
	public abstract void calculateNeighbours();
	
	public void insertParticles(Set<Particle> particles){
		for(Particle p: particles){
			insert(p);
		}
	}
	
	public void insert(Particle p){
		int x = (int) (Math.floor((p.rx+offset)/(L/M)));
		int y = (int) (Math.floor((p.ry+offset)/(L/M)));
		cells[x][y].getParticles().add(p);
	}
	
	public void remove(Particle p){
		int x = (int) (Math.floor((p.rx+offset)/(L/M)));
		int y = (int) (Math.floor((p.ry+offset)/(L/M)));
		cells[x][y].getParticles().remove(p);
	}
	
	public Cell getCell(int x, int y){
		return cells[x][y];
	}
	
	public Cell getCell(Particle p){
		return getCell(p.rx,p.ry);
	}
	
	public Cell getCell(double x, double y){
		int cx = (int) (Math.floor((x+offset)/(L/M)));
		int cy = (int) (Math.floor((y+offset)/(L/M)));

		if(cx<0 || cx>=M || cy<0 || cy>=M){
			return null;
		}
		return cells[cx][cy];
	}
	
	public Cell[][] getGrid(){
		return cells;
	}
	
	public int getM(){
		return M;
	}
	
	public double getL() {
		return L;
	}
}
