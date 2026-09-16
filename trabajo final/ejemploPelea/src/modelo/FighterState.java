package modelo;

public class FighterState {
	private final String id;
    private int hp;
    private String currentAnimation;

    public FighterState(String id, int hp, String initialAnimation) {
        this.id = id;
        this.hp = hp;
        this.currentAnimation = initialAnimation;
    }

    public String getId() { return id; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = Math.max(0, hp); }
    public String getCurrentAnimation() { return currentAnimation; }
    public void setCurrentAnimation(String animation) { this.currentAnimation = animation; }
}
