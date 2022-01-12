function eliminar(id){
swal({
  title: "Estas seguro/a de eliminar?",
  text: "Una vez eliminado, ¡no podrá recuperar este usuario!",
  icon: "Peligro",
  buttons: true,
  dangerMode: true,
})
.then((OK) => {
  if (OK) {
    $.ajax({
    url:"/eliminar/"+id,
    success: function(res){
    console.log(res)
    }
    });
    swal("Poof! Tu Usuario ha sido eliminado correctamente.", {
      icon: "success",
    }).then((ok)=>{
        if(ok){
        location.href="/listar";
        }
    });
  } else {
    swal("Tu usuario se salvo!");
  }
});
}