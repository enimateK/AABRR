public class AABRR_SEARCH {

    public boolean rechercherAABRR(AABRR a, int i){
        if(a != null){
            if(a.getMin() <= i && a.getMax() >= i){
                System.out.println("Dans interval M m : " + a.getMin()+ " "+ a.getMax());
                rechercherABRR(a.getAA(), i);
                return true;
            }

            else{
                if(a.getMin() > i){
                    return rechercherAABRR(a.getSAG(), i);
                }
                if(a.getMax() < i){
                    return rechercherAABRR(a.getSAD(), i);
                }
            }

        }
        return false;
    }

    public void rechercherABRR(ABRR a, int i){
        if(a != null){
            if(a.getRacine() == i){
                System.out.println("Valeur trouvée dans ABRR " + a.getRacine());
            }

            else{
                if(a.getRacine()> i){
                    rechercherABRR(a.getSAG(), i);
                }
                if(a.getRacine()< i){
                    rechercherABRR(a.getSAD(), i);
                }
            }

        }
    }
}
