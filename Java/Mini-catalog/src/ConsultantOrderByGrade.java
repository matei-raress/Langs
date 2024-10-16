class ConsultantOrderByGrade implements Consultant
{
    @Override
    public void ExecuteOrder66(StudentClass v) {
        Student aux;
        for (int i = 0; i < v.Getn()-1 ; i++){
            for (int j = 0; j < v.Getn()-i-1 ; j++)
                if (v.v[j].compareGrade(v.v[j+1]) > 0){
                    aux = v.v[j];
                    v.v[j] = v.v[j+1];
                    v.v[j+1] = aux;
                }
        }
    }
}