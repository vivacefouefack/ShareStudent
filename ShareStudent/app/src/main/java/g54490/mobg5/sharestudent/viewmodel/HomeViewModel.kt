package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication

class HomeViewModel:ViewModel() {

    private var _allPublication = MutableLiveData<List<Publication>>()
    val allPublication: LiveData<List<Publication>>
        get() = _allPublication

    //FIXME (QHB) :this property should be renamed, as it's not a Button class.
    private var _addButton = MutableLiveData<Boolean?>()
    val addButton: LiveData<Boolean?>
        get() = _addButton

    private val _navigateToPublicationDetail = MutableLiveData<Int>()
    val navigateToPublicationDetail
        get() = _navigateToPublicationDetail

    fun onPublicationClicked(id: Int) {
        _navigateToPublicationDetail.value = id
    }

    fun onPublicationDetailNavigated() {
        _navigateToPublicationDetail.value = null
    }

    init {
        _allPublication.value=null
        _addButton.value=null
        //_navigateToPublicationDetail.value=1
        getData()
    }

    fun setAddButton(){
        _addButton.value=false
    }

    //FIXME (QHB) : those 2 methods are ok if it's only temporary code to test your app. Not in the final release.
    // You should create a custom class Repository to hold all the data used by the app and communicate with database
    // and api.
    fun getData(){
        _allPublication.value= listOf(Publication(4,"Clé usb de 32Go","entreprise d'import export"),
                                      Publication(7," Framework spring","entreprise d'import export1"),
                                      Publication(5," Framework angular","entreprise d'import export2"),
                                      Publication(6,"Clé usb de 64Go","entreprise d'import export3"),
            Publication(7,"africain2","entreprise d'import export2"),
            Publication(4,"africain3","entreprise d'import export3"),
            Publication(6,"africain4","entreprise d'import export"),
            Publication(5,"africain5","entreprise d'import export1"),
            Publication(5,"africain6","entreprise d'import export2"),
            Publication(7,"africain7","entreprise d'import export3"))
    }

    fun addPublication(pub:Publication){
        _allPublication.value= listOf(pub,Publication(4,"Clé usb de 32Go","entreprise d'import export"),
            Publication(7," Framework spring","entreprise d'import export1"),
            Publication(5," Framework angular","entreprise d'import export2"),
            Publication(6,"Clé usb de 64Go","entreprise d'import export3"),
            Publication(7,"africain2","entreprise d'import export2"),
            Publication(4,"africain3","entreprise d'import export3"),
            Publication(6,"africain4","entreprise d'import export"),
            Publication(5,"africain5","entreprise d'import export1"),
            Publication(5,"africain6","entreprise d'import export2"),
            Publication(7,"africain7","entreprise d'import export3"))
    }

    fun goToAddPublicationUi(){
        _addButton.value=true
    }
}