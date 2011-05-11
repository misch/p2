package ursuppe;

public class LifeExpectancyGene extends AbstractGene {
	
	public LifeExpectancyGene(Game game){
		super(game);
		super.price=5;
		super.mutationPoints=5;
	}

	@Override
	public void activate(Amoeba amoeba) {
		amoeba.setFatalDamage(3);
	}
	public void deactivate(Amoeba amoeba){
		amoeba.setFatalDamage(2);
	}

}
