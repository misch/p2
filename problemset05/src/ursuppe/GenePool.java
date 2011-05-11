package ursuppe;

import java.util.ArrayList;

public class GenePool {
	
	private ArrayList<IGene> availableGenes=new ArrayList<IGene>();
	private ArrayList<IGene> soldGenes=new ArrayList<IGene>();
	private Game game;
	
	
	public GenePool(Game game){
		this.game=game;
		this.initGenes();
		
	}


	private void initGenes() {
		availableGenes.add(new IntelegenceGene(this.game));
		availableGenes.add(new LifeExpectancyGene(this.game));
		availableGenes.add(new DivisionRateGene(this.game));
		availableGenes.add(new FrugalityGene(this.game));
	}


	public ArrayList<IGene> getAvailableGenes() {
		return availableGenes;
	}
	public void buyGene(IGene gene, Player player){
		availableGenes.remove(gene);
		soldGenes.add(gene);
		player.pay(gene.getPrice());
		player.addGene(gene);
	}
	public void returnGene(IGene gene, Player player){
		soldGenes.remove(gene);
		availableGenes.add(gene);
		player.removeGene(gene);
	}

}
