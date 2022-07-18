//package com.mariots.biblioteca.bibliotecawebadmin.api.mapper;
//
//import com.mariots.biblioteca.bibliotecawebadmin.api.exceptions.CampoEnBlancoException;
//import com.mariots.biblioteca.bibliotecawebadmin.api.exceptions.RecursoNoEncontradoException;
//import com.mariots.biblioteca.bibliotecawebadmin.api.models.Autor;
//import com.mariots.biblioteca.bibliotecawebadmin.api.models.Supertema;
//import com.mariots.biblioteca.bibliotecawebadmin.api.models.Tema;
//import com.mariots.biblioteca.bibliotecawebadmin.api.models.Texto;
//import com.mariots.biblioteca.bibliotecawebadmin.repository.RepositoryBiblioteca;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class MapperImpl implements Mapper{
//
//    @Autowired
//    RepositoryBiblioteca repository;
//
//    //ENTITY -->> DTO
//    @Override
//    public Autor toDto(AutorEntity autorEntity) {
//        Autor autor = new Autor();
//        autor.setIdAutor(autorEntity.getIdAutor());
//        autor.setNombreAutor(autorEntity.getNombreAutor());
//        autor.setFechaAutor(autorEntity.getFechaAutor());
//        autor.setDescripcionBreve(autorEntity.getDescripcionBreve());
//        autor.setDescripcionLarga(autorEntity.getDescripcionLarga());
//        if (autorEntity.getTextos() == null){
//        } else autor.setIdTextos(autorEntity.getTextos().stream().map((t)->(t.getIdTexto())).collect(Collectors.toList()));
//        return autor;
//    }
//
//    @Override
//    public Texto toDto(TextoEntity textoEntity) {
//        Texto texto =new Texto();
//        texto.setIdTexto(textoEntity.getIdTexto());
//        texto.setTextoString(textoEntity.getTextoString());
//        texto.setLongitud(textoEntity.getLongitud());
//        if (textoEntity.getTemas() == null) {
//        } else texto.setIdTemas(textoEntity.getTemas().stream().map(TemaEntity::getIdTema).collect(Collectors.toList()));
//        if (textoEntity.getAutor() == null) {
//        } else texto.setIdAutor(textoEntity.getAutor().getIdAutor());
//        return texto;
//    }
//
//    @Override
//    public Tema toDto(TemaEntity temaEntity) {
//        Tema tema = new Tema();
//        tema.setIdTema(temaEntity.getIdTema());
//        tema.setNombreTema(temaEntity.getNombreTema());
//        if (temaEntity.getSupertema() == null) {
//        } else tema.setIdSupertema(temaEntity.getSupertema().getIdSupertema());
//        if (temaEntity.getTextos() == null) {
//        } else tema.setIdTextos(temaEntity.getTextos().stream().map(TextoEntity::getIdTexto).collect(Collectors.toList()));
//        return tema;
//    }
//
//    @Override
//    public Supertema toDto(SupertemaEntity supertemaEntity) {
//        Supertema supertema = new Supertema();
//        supertema.setIdSupertema(supertemaEntity.getIdSupertema());
//        supertema.setNombreSupertema(supertemaEntity.getNombreSupertema());
//        if(supertemaEntity.getTemas() == null){
//        } else supertema.setIdTemas(supertemaEntity.getTemas().stream().map(TemaEntity::getIdTema).collect(Collectors.toList()));
//        return supertema;
//    }
//
//    //DTO -->> ENTITY
//    @Override
//    public AutorEntity toEntity(Autor autor) {
//        AutorEntity autorEntity = new AutorEntity();
//        if (autor.getNombreAutor()==null){
//            throw new CampoEnBlancoException("nombreAutor");
//        }
//        //El id se define en BD
//        autorEntity.setNombreAutor(autor.getNombreAutor());
//        autorEntity.setFechaAutor(autor.getFechaAutor());
//        autorEntity.setDescripcionBreve(autor.getDescripcionBreve());
//        autorEntity.setDescripcionLarga(autor.getDescripcionLarga());
//        if (autor.getIdTextos() == null) {
//        } else {
//            List<TextoEntity> listaTextosEntity= new ArrayList<>();
//            autor.getIdTextos().stream().map((id)->listaTextosEntity.add(repository.recuperarTextoPorId(id).get())).collect(Collectors.toList());
//            autorEntity.setTextos(listaTextosEntity);
//        }
//        return autorEntity;
//    }
//
//    @Override
//    public SupertemaEntity toEntity(Supertema supertema) {
//        SupertemaEntity supertemaEntity = new SupertemaEntity();
//        if(supertema.getNombreSupertema()==null){
//            throw new CampoEnBlancoException("nombreSupertema");
//        }
//
//        supertemaEntity.setNombreSupertema(supertema.getNombreSupertema());
//        if (supertema.getIdTemas() == null) {
//        } else {
//            List<TemaEntity> listaTemasEntity = new ArrayList<>();
//            supertema.getIdTemas().stream().map((id)->listaTemasEntity.add(repository.recuperarTemaPorId(id).get())).collect(Collectors.toList());
//            supertemaEntity.setTemas(listaTemasEntity);
//        }
//        return supertemaEntity;
//    }
//
//    @Override
//    public TemaEntity toEntity(Tema tema) {
//        TemaEntity temaEntity= new TemaEntity();
//        if (tema.getNombreTema()==null){
//            throw new CampoEnBlancoException("nombreTema");
//        }
//        temaEntity.setNombreTema(tema.getNombreTema());
//        if (temaEntity.getTextos() == null) {
//        }else{
//            List<TextoEntity> listaTextosEntity = new ArrayList<>();
//            tema.getIdTextos().stream().map((id)->listaTextosEntity.add(repository.recuperarTextoPorId(id).get())).collect(Collectors.toList());
//            temaEntity.setTextos(listaTextosEntity);
//        }
//        return temaEntity;
//    }
//
//    @Override
//    public TextoEntity toEntity(Texto texto) {
//        TextoEntity textoEntity = new TextoEntity();
//        if (texto.getTextoString()==null){
//            throw new CampoEnBlancoException("textoString");
//        }
//        textoEntity.setTextoString(texto.getTextoString());
//        textoEntity.setLongitud(texto.getLongitud());
//        if (texto.getIdAutor() == null) {
//        } else{
//           textoEntity.setAutor(repository.recuperarAutorPorId(texto.getIdAutor()).get());
//        }
//        if (texto.getIdTemas() == null) {
//        } else{
//            List<TemaEntity> listaTemasEntity = new ArrayList<>();
//            texto.getIdTemas().stream().map((id)->listaTemasEntity.add(repository.recuperarTemaPorId(id).get())).collect(Collectors.toList());
//            textoEntity.setTemas(listaTemasEntity);
//        }
//        return textoEntity;
//    }
//
//    //NUEVO-REST --> DTO
//    @Override
//    public Autor toDto(AutorRest autorRest) {
//        return new Autor().builder()
//                .nombreAutor(autorRest.getNombreAutor())
//                .fechaAutor(autorRest.getFechaAutor())
//                .descripcionBreve(autorRest.getDescripcionBreve())
//                .descripcionLarga(autorRest.getDescripcionLarga())
//                .build();
//    }
//
//    @Override
//    public Texto toDto(TextoRest textoRest) {
//        Texto texto = new Texto().builder()
//                .textoString(textoRest.getTextoString())
//                .longitud(textoRest.getLongitud())
//                .build();
//        List<Integer> idTemas = new ArrayList<>();
//        //comprobamos si el nombre de autor se ha introducido
//        if(textoRest.getNombreAutor()==null) {
//            //no se ha introducido --> excepción
//            throw new CampoEnBlancoException();
//        } else {
//            //se ha introducido -->
//            //comprobamos si existe el nombre de autor en BD
//            if(repository.recuperarAutorPorNombre(textoRest.getNombreAutor()).isPresent()){
//                //existe entonces añadimos el autor al textoDto
//                int idAutor =repository.recuperarAutorPorNombre(textoRest.getNombreAutor()).get().getIdAutor();
//                texto.setIdAutor(idAutor);
//            } else {
//                //no existe --> excepción y pedimos otro
//                throw new RecursoNoEncontradoException("No existe el nombre de autor aportado, ingrese un nombre de autor previamente registrado");
//            }
//        }
//        //comprobamos si existen los temas aportados
//        if(textoRest.getNombreTemas()==null){
//            throw new CampoEnBlancoException();
//        } else {
//            //comprobamos si existen en BD los temas aportados, si hay alguno que no está en BD se lanza ex
//            if(textoRest.getNombreTemas().stream().allMatch((nombreTema)->repository.recuperarTemaPorNombre(nombreTema).isPresent())){
//                textoRest.getNombreTemas().stream().map((nombreTema)->idTemas.add(repository.recuperarTemaPorNombre(nombreTema).get().getIdTema())).collect(Collectors.toList());
//                texto.setIdTemas(idTemas);
//            } else {
//                throw new RecursoNoEncontradoException("No existe el nombre de tema aportado, ingrese un nombre de autor previamente registrado");
//            }
//        }
//    return texto;
//    }
//
//    @Override
//    public Tema toDto(TemaRest temaRest) {
//        return new Tema().builder()
//                .nombreTema(temaRest.getNombreTema())
//                .build();
//    }
//
//    @Override
//    public Supertema toDto(SupertemaRest supertemaRest) {
//        return new Supertema().builder()
//                .nombreSupertema(supertemaRest.getNombreSupertema())
//                .build();
//    }
//
//}
