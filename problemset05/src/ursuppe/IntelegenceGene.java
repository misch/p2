package ursuppe;

public class IntelegenceGene extends AbstractGene {
	
	
	public IntelegenceGene(Game game){
		super(game);
		super.price=2;
		super.mutationPoints=3;
	}
	@Override
	public void activate(Amoeba amoeba) {
		
	}
	@Override
	public void deactivate(Amoeba amoeba) {
		
	}

}
