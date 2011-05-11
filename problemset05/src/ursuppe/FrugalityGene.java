package ursuppe;

public class FrugalityGene extends AbstractGene {
	
	public FrugalityGene(Game game){
		super(game);
		super.price=6;
		super.mutationPoints=5;
	}

	@Override
	public void activate(Amoeba amoeba) {
		amoeba.setNeedeFood(2);
	}

	@Override
	public void deactivate(Amoeba amoeba) {
		amoeba.setNeedeFood(3);
		
	}
	

}
