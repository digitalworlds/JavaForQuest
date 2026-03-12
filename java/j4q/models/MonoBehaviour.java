package j4q.models;

public abstract class MonoBehaviour extends Component{

    public MonoBehaviour(){
        Start();
    }

    public abstract void Start();
    public abstract void Update();

}
