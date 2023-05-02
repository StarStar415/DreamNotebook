package course.java.project.dreamnotebook.object;

public class ToastAnimationTime {
    // toast 的持續時間
    private int toastDelay=1500;
    private int fadeInDelay=200;
    private int fadeOutDelay=200;

    public void setToastDelay(int toastDelay){
        this.toastDelay = toastDelay;
    }
    public void setFadeInDelay(int fadeInDelay) {
        this.fadeInDelay = fadeInDelay;
    }

    public void setFadeOutDelay(int fadeOutDelay){
        this.fadeOutDelay = fadeOutDelay;
    }

    public int getToastDelay(){
        return this.toastDelay;
    }
    public int getFadeInDelay() {
        return this.fadeInDelay;
    }

    public int getFadeOutDelay(){
        return this.fadeOutDelay;
    }
}
