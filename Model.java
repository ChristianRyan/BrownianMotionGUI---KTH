
class Model {

	Particle[] particle; // the object array consisting of all the particles.
	int nrOfParticles;
	boolean Map[][] = new boolean[750][700];

	Model(int nrOfParticles) {
		this.nrOfParticles = nrOfParticles;
		particle = new Particle[nrOfParticles];
		for (int i = 0; i < this.nrOfParticles; i++) {
			particle[i] = new Particle(375, 300);

		}
	}

	// Skapar partiklarna
	public void moveAll() {
		for (int i = 0; i < this.nrOfParticles; i++) {
			particle[i].randomMove();
		}
	}

	public void updateMap() {
		for (int i = 0; i < nrOfParticles; i++) {
			if (!particle[i].isMoving) {
				for (int j = -3; j < 5; j++) {
					for (int k = -3; k < 5; k++) {
						if ((int) (Math.round(particle[i].x) + k) >= 0 && (int) (Math.round(particle[i].x) + k) <= 747
								&& ((int) (Math.round(particle[i].y) + j) >= 0
										&& (int) (Math.round(particle[i].y) + j) <= 697)) {
							Map[(int) (Math.round(particle[i].x) + k)][(int) (Math.round(particle[i].y) + j)] = true;
						} else {
						}
					}
				}
			}
		}
	}

	public void checkMap() {
		for (int i = 0; i < nrOfParticles; i++) {
			try {
				if (Map[(int) (Math.round(particle[i].x))][(int) (Math.round(particle[i].y))]) {
					particle[i].isMoving = false;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
	}

	
	public void setL(double value) {
		for (int i = 0; i < this.nrOfParticles; i++) {
			particle[i].L = value;
		}

	}
}