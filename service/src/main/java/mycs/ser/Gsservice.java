package mycs.ser;

import mycs.daos.GsDao;
import mycs.en.Gs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class Gsservice {

    @Autowired
    private GsDao gsDao;

    public List<Gs> queryGs(int pageNo, int pageSize) {
        return gsDao.queryGs(pageNo,pageSize);
    }

    public int gsqushangye(int pageNo, int pageSize) {
        return gsDao.gsqushangye(pageNo,pageSize);
    }

    public int gssums(int pageSize) {
        return gsDao.gssums(pageSize);
    }

    public List<Gs> qugs() {
        return gsDao.qugs();
    }

    public void gsadd(Gs gs) {
        gsDao.gsadd(gs);
    }

    public Boolean gsdelete(int id) {
        return gsDao.gsdelete(id);
    }

    public void gsupdate(Gs gs) {
        gsDao.gsupdate(gs);
    }

    public Gs quidgs(int sid) {
        return gsDao.quidgs(sid);
    }
}
