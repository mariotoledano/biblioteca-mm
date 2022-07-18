package com.mariots.biblioteca.bibliotecarest.core.service;

import com.mariots.biblioteca.bibliotecarest.api.exceptions.*;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.AutorDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.SupertemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TemaDto;
import com.mariots.biblioteca.bibliotecarest.core.model.dto.TextoDto;
import com.mariots.biblioteca.bibliotecarest.api.mapper.Mapper;
import com.mariots.biblioteca.bibliotecarest.core.model.nuevosrecurso.*;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TemaSupertema;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TextoAutor;
import com.mariots.biblioteca.bibliotecarest.core.model.clasesempaquetado.TextoTema;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.AutorEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.SupertemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TemaEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.entities.TextoEntity;
import com.mariots.biblioteca.bibliotecarest.persistence.repository.RepositoryBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceBibliotecaImpl implements ServiceBiblioteca {
    @Autowired
    RepositoryBiblioteca repository;
   @Autowired
    Mapper mapper;
   
//GUARDAR NUEVO
    @Override
    public AutorDto guardarAutor(AutorNuevo autorNuevo) {
        if (autorNuevo.getNombreAutor()==null|| autorNuevo.getNombreAutor().isEmpty()){
            throw new CampoEnBlancoException("nombreAutor");
        }
        if (repository.recuperarAutores().stream()
                .anyMatch((autor)->Objects.equals(autor.getNombreAutor(),(autorNuevo.getNombreAutor())))){
            throw new DuplicarRecursoException("Ya existe un autor con ese nombre de autor");
        }
        AutorEntity autorEntity = mapper.toEntity(mapper.toDto(autorNuevo));
        return mapper.toDto(repository.guardarAutor(autorEntity));
    }

    @Override
    public TemaDto guardarTema(TemaNuevo temaNuevo) {
        if(temaNuevo.getNombreTema()==null|| temaNuevo.getNombreTema().isEmpty()){
            throw new CampoEnBlancoException("nombreTema");
        }
        if (repository.recuperarTemas().stream()
                .anyMatch((tema)->Objects.equals(tema.getNombreTema(), temaNuevo.getNombreTema()))){
            throw new DuplicarRecursoException("Ya existe un tema con ese nombre de tema");
        }
        TemaEntity temaEntity = mapper.toEntity(mapper.toDto(temaNuevo));
        return mapper.toDto(repository.guardarTema(temaEntity));
    }

    @Override
    public SupertemaDto guardarSupertema(SupertemaNuevo supertemaNuevo) {
        if (supertemaNuevo.getNombreSupertema()==null|| supertemaNuevo.getNombreSupertema().isEmpty()){
            throw new CampoEnBlancoException("nombreSupertema");
        }
        if (repository.recuperarSupertemas().stream()
                .anyMatch((supertema)->Objects.equals(supertema.getNombreSupertema(), supertemaNuevo.getNombreSupertema()))){
            throw new DuplicarRecursoException("Ya existe un supertema con ese nombre de supertema");
        }
        SupertemaEntity supertemaEntity = mapper.toEntity(mapper.toDto(supertemaNuevo));
        return mapper.toDto(repository.guardarSupertema(supertemaEntity));
    }

    @Override
    public TextoDto guardarTexto(TextoNuevo textoNuevo) {
        if(textoNuevo.getTextoString()==null|| textoNuevo.getTextoString().isEmpty()){
            throw new CampoEnBlancoException("textoString");
        }
        if (repository.recupearTextos().stream()
                .anyMatch((texto)->Objects.equals(texto.getTextoString(), textoNuevo.getTextoString()))){
            throw new DuplicarRecursoException("Ya existe un texto igual");
        }
        //comprobamos el autor aportado
        if(textoNuevo.getNombreAutor()==null|| textoNuevo.getNombreAutor().isEmpty()){
            throw new CampoEnBlancoException("nombreAutor");
        } else if (repository.recuperarAutorPorNombre(textoNuevo.getNombreAutor()) == null) {
            throw new RecursoNoEncontradoException("No existe el nombre de autor aportado, ingrese un nombre de autor previamente registrado");
        }
        //comprobamos el tema aportado
        if(textoNuevo.getNombreTemas()==null|| textoNuevo.getNombreTemas().isEmpty()){
            throw new CampoEnBlancoException("nombreTemas");
        } else if (textoNuevo.getNombreTemas().stream()
                .anyMatch((nombreTema)->repository.recuperarTemaPorNombre(nombreTema)==null)){
            throw new RecursoNoEncontradoException("No existe el nombre de tema aportado, ingrese un nombre previamente registrado");
        }
        TextoEntity textoEntity = mapper.toEntity(mapper.toDto(textoNuevo));
        return mapper.toDto(repository.guardarTexto(textoEntity));
    }

    @Override
    public TextoDto guardarTextoDesdePath(TextoNuevo textoNuevo, int idAutor, int idTema) {
        if(textoNuevo.getTextoString()==null|| textoNuevo.getTextoString().isEmpty()){
            throw new CampoEnBlancoException("textoString");
        }
        if (repository.recupearTextos().stream()
                .anyMatch((texto)->Objects.equals(texto.getTextoString(), textoNuevo.getTextoString()))){
            throw new DuplicarRecursoException("Ya existe un texto igual");
        }
        //idAutor e idTema no pueden ser nulos al venir de path
        TextoNuevo textoAGuardar = textoNuevo.builder()
                .textoString(textoNuevo.getTextoString())
                .longitud(textoNuevo.getLongitud())
                .nombreAutor(repository.recuperarAutorPorId(idAutor)
                        .orElseThrow(()->new RecursoNoEncontradoException("Id de Autor no encontrado")).getNombreAutor())
                .nombreTemas(Arrays.asList(
                        repository.recuperarTemaPorId(idTema)
                                .orElseThrow(()->new RecursoNoEncontradoException("Id de Tema no encontrado")).getNombreTema())                )
                .build();
        return guardarTexto(textoAGuardar);
    }

//MÉTODOS RECUPERAR TODOS
    @Override
    public List<AutorDto> recuperarAutores() {
        List<AutorEntity> todosAutores = repository.recuperarAutores();
        if (todosAutores ==null||todosAutores.isEmpty()){
            throw new NoHayResultadosException("No hay autores registrados en la BBDD");
        }
        return todosAutores.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TemaDto> recuperarTemas() {
        List<TemaEntity> todosTemas = repository.recuperarTemas();
        if (todosTemas==null||todosTemas.isEmpty()){
            throw new NoHayResultadosException("No hay temas registrados en la BBDD");
        }
        return todosTemas.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SupertemaDto> recuperarSupertemas() {
        List<SupertemaEntity> todosSupertemas = repository.recuperarSupertemas();
        if (todosSupertemas==null|| todosSupertemas.isEmpty()){
            throw new NoHayResultadosException("No hay supertemas registrados en la BBDD");
        }
        return todosSupertemas.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TextoDto> recuperarTextos() {
        List<TextoEntity> todosTextos = repository.recupearTextos();
        if (todosTextos==null||todosTextos.isEmpty()){
            throw new NoHayResultadosException("No hay textos registrados en la BBDD");
        }
        return todosTextos.stream().map(mapper::toDto).collect(Collectors.toList());
    }

//RECUPERAR POR ID
    @Override
    public AutorDto recuperarAutorPorId(int idAutor) {
        return repository.recuperarAutorPorId(idAutor).map(mapper::toDto)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de Autor no encontrado"));
    }

    @Override
    public TemaDto recuperarTemaPorId(int idTema) {
        return repository.recuperarTemaPorId(idTema).map(mapper::toDto)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de Tema no encontrado"));
    }

    @Override
    public SupertemaDto recuperarSupertemaPorId(int idSupertema) {
        return repository.recuperarSupertemaPorId(idSupertema).map(mapper::toDto)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de Supertema no encontrado"));
    }

    @Override
    public TextoDto recuperarTextoPorId(int idTexto) {
        return repository.recuperarTextoPorId(idTexto).map(mapper::toDto)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de Texto no encontrado"));
    }

//RECUPERAR POR NOMBRE
    @Override
    public AutorDto recuperarAutorPorNombre(String nombreAutor) {
        return repository.recuperarAutorPorNombre(nombreAutor).map(mapper::toDto)
                .orElseThrow(()->new RecursoNoEncontradoException("Nombre de Autor no encontrado"));
    }

    @Override
    public TemaDto recuperarTemaPorNombre(String nombreTema) {
        return repository.recuperarTemaPorNombre(nombreTema).map(mapper::toDto)
                .orElseThrow(()->new RecursoNoEncontradoException("Nombre de Tema no encontrado"));    }

    @Override
    public SupertemaDto recuperarSupertemaPorNombre(String nombreSupertema) {
        return repository.recuperarSupertemaPorNombre(nombreSupertema)
                .map(mapper::toDto).orElseThrow(()->new RecursoNoEncontradoException("Nombre de Supertema no encontrado"));
    }
//RECUPERAR SEGÚN OTRO RESOURCE
    @Override
    public List<TextoDto> recuperarTextosPorAutor(int idAutor) {
        recuperarAutorPorId(idAutor);
        return recuperarTextos().stream().filter((texto) -> texto.getIdAutor() == idAutor).collect(Collectors.toList());
    }

    @Override
    public List<TextoDto> recuperarTextosPorTema(int idTema) {
        TemaDto temaDto = recuperarTemaPorId(idTema);
        List<TextoDto> resultado = recuperarTextos()
                .stream()
                .filter(
                        (texto)->texto.getIdTemas()
                                .stream()
                                .anyMatch((tema)->tema==idTema)==true)
                .collect(Collectors.toList());
        if (resultado.isEmpty()||resultado==null){
            throw new NoHayResultadosException("No hay textos vinculados con este tema");
        }
        return resultado;
    }

    @Override
    public List<TextoDto> recuperarTextosPorSupertema(int idSupertema) {
        SupertemaDto supertemaDto = recuperarSupertemaPorId(idSupertema);
        List<TextoDto> resultado = new ArrayList<>();
        List<Integer> idTemasEnSupertema = supertemaDto.getIdTemas();
        for (int idTemaEnSupertema:idTemasEnSupertema) {
            List<TextoDto> textosDeTemaAsociado = recuperarTextosPorTema(idTemaEnSupertema);
            for(TextoDto texto:textosDeTemaAsociado){
                resultado.add(texto);
            }
        }
        if(resultado.isEmpty()){
            throw new NoHayResultadosException("No hay textos vinculados con este supertema");
        }
        return resultado;
    }

    @Override
    public List<TemaDto> recuperarTemasPorSupertema(int idSupertema) {
        recuperarSupertemaPorId(idSupertema);
        List<TemaDto> resultado = recuperarTemas().stream()
                .filter((tema) -> tema.getIdSupertema() != null && tema.getIdSupertema() == idSupertema)
                .collect(Collectors.toList());
        if (resultado.isEmpty()){
            throw new NoHayResultadosException("No hay temas vinculados co este supertema");
        }
        return resultado;
    }

    //AÑADIR VÍNCULOS
    @Override
    public TemaSupertema vincularTemaSupertema(int idTema, int idSupertema) {
        TemaEntity temaEntity = repository.recuperarTemaPorId(idTema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de tema no encontrado "));
        SupertemaEntity supertemaEntity = repository.recuperarSupertemaPorId(idSupertema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de tema no encontrado "));
        //Comprobamos si el supertema de temaEntity es null, si lo es añadimos vínculo
        if(temaEntity.getSupertema()==null){
            temaEntity.setSupertema(supertemaEntity);
            repository.guardarTema(temaEntity);
            //Comprobamos si el vinculo existe previamente para no generar duplicados
        } else if (temaEntity.getSupertema()==supertemaEntity) {
            throw new RepetirVinculoException();
            //si no es nulo ni existe el vínculo previamente, entonces existe un vínculo y hay que sobreescribir
        } else{
            throw new RecursoYaVinculadoException();
        }
        TemaDto temaDespues = recuperarTemaPorId(idTema);
        SupertemaDto supertemaDespues =recuperarSupertemaPorId(idSupertema);
        return new TemaSupertema(temaDespues,supertemaDespues);
    }

    @Override
    public TemaSupertema sobreescribirVinculoTemaSupertema(int idTema, int idSupertema) {
        TemaEntity temaEntity = repository.recuperarTemaPorId(idTema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de tema no encontrado "));
        SupertemaEntity supertemaEntity = repository.recuperarSupertemaPorId(idSupertema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de supertematema no encontrado "));
        //Comprobamos que el supertema de tema no es null
        if(temaEntity.getSupertema()==null){
            throw new RecursoNoVinculadoException();
            //Comprobamos si existe previamente el vínculo para no generar duplicados
        } else if(temaEntity.getSupertema()==supertemaEntity) {
            throw new RepetirVinculoException();
        } else{
            temaEntity.setSupertema(supertemaEntity);
            repository.guardarTema(temaEntity);
        }
        TemaDto temaDespues = recuperarTemaPorId(idTema);
        SupertemaDto supertemaDespues =recuperarSupertemaPorId(idSupertema);
        return new TemaSupertema(temaDespues,supertemaDespues);
    }

    @Override
    public TextoTema vincularTextoTema(int idTexto, int idTema) {
        TextoEntity textoEntity = repository.recuperarTextoPorId(idTexto)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de texto no encontrado"));
        TemaEntity temaEntity= repository.recuperarTemaPorId(idTema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de tema no encontrado"));
        //Comprobamos si TextoEntity no tiene temas asociados (en teoría imposible), si lo es se añade vínculo
        if(textoEntity.getTemas()==null){
            List<TemaEntity> temasEnTexto = Arrays.asList(temaEntity);
            textoEntity.setTemas(temasEnTexto);
            repository.guardarTexto(textoEntity);
            //comprobamos si el vínculo existía previamente para evitar duplicados
        } else if(textoEntity.getTemas().stream().anyMatch((tema)->tema==temaEntity)){
            throw new RepetirVinculoException();
        } else{
            List<TemaEntity> temasEnTexto = textoEntity.getTemas();
            temasEnTexto.add(temaEntity);
            textoEntity.setTemas(temasEnTexto);
            repository.guardarTexto(textoEntity);
        }
        TextoDto textoDespues = recuperarTextoPorId(idTexto);
        TemaDto temaDespues = recuperarTemaPorId(idTema);
        return new TextoTema(textoDespues,temaDespues);
    }

    @Override
    public TextoAutor vincularTextoAutor(int idTexto, int idAutor) {
        TextoEntity textoEntity = repository.recuperarTextoPorId(idTexto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de texto no encontrado"));
        AutorEntity autorEntity = repository.recuperarAutorPorId(idAutor)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de autor no encontrado"));
        //Comprobamos si el vínculo texto autor es nulo, si lo es se añade y guarda
        if(textoEntity.getAutor()==null){
            textoEntity.setAutor(autorEntity);
            repository.guardarTexto(textoEntity);
            //comprobamos si el vínculo que se quiere crear ya existe para eviar repeticiones
        } else if (textoEntity.getAutor()==autorEntity) {
            throw new RepetirVinculoException();
        } else{
            throw new RecursoYaVinculadoException();
        }
        TextoDto textoDespues = recuperarTextoPorId(idTexto);
        AutorDto autorDespues = recuperarAutorPorId(idAutor);
        return  new TextoAutor(textoDespues,autorDespues);
    }

    @Override
    public TextoAutor sobreescribirVinculoTextoAutor(int idTexto, int idAutor) {
        TextoEntity textoEntity = repository.recuperarTextoPorId(idTexto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de texto no encontrado"));
        AutorEntity autorEntity = repository.recuperarAutorPorId(idAutor)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de autor no encontrado"));
        //comprobamos si el autor es null
        if(textoEntity.getAutor()==null){
            throw new RecursoNoVinculadoException();
            //comprobamos si el vínculo ya existía para no duplicar elementos de vínculo
        } else if(textoEntity.getAutor()==autorEntity){
            throw new RepetirVinculoException();
            //si no existe previamente se añade
        } else {
            textoEntity.setAutor(autorEntity);
            repository.guardarTexto(textoEntity);
        }
        TextoDto textoDespues = recuperarTextoPorId(idTexto);
        AutorDto autorDespues = recuperarAutorPorId(idAutor);
        return  new TextoAutor(textoDespues,autorDespues);
    }

//ELIMINAR VÍNCULOS
    @Override
    public TemaSupertema desvincularTemaSupertema(int idTema, int idSupertema) {
        TemaEntity temaEntity = repository.recuperarTemaPorId(idTema)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de tema no encontrado"));
        SupertemaEntity supertemaEntity = repository.recuperarSupertemaPorId(idSupertema)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de supertema no encontrado"));

        if(temaEntity.getSupertema()==null ||
                temaEntity.getSupertema()!=supertemaEntity){
            throw new RecursoNoVinculadoException();
        } else{
            temaEntity.setSupertema(null);
            repository.guardarTema(temaEntity);
        }
        TemaDto temaDespues = recuperarTemaPorId(idTema);
        SupertemaDto supertemaDespues = recuperarSupertemaPorId(idSupertema);
        return new TemaSupertema(temaDespues, supertemaDespues);
    }

    @Override
    public TextoTema desvincularTextoTema(int idTexto, int idTema) {
        TextoEntity textoEntity = repository.recuperarTextoPorId(idTexto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de texto no encontrado"));
        TemaEntity temaEntity = repository.recuperarTemaPorId(idTema)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de tema no encontrado"));
        if (textoEntity.getTemas()==null
                || textoEntity.getTemas().stream().noneMatch((tema)->tema==temaEntity)){
            throw new RecursoNoVinculadoException();
        } else{
            List<TemaEntity> temasEnTexto = textoEntity.getTemas();
            int indexTemaEntity = temasEnTexto.indexOf(temaEntity);
            temasEnTexto.remove(indexTemaEntity);
            textoEntity.setTemas(temasEnTexto);
            repository.guardarTexto(textoEntity);
        }
        TextoDto textoDespues = recuperarTextoPorId(idTexto);
        TemaDto temaDespues = recuperarTemaPorId(idTema);
        return new TextoTema(textoDespues, temaDespues);
    }

    @Override
    public TextoAutor desvincularTextoAutor(int idTexto, int idAutor) {
        TextoEntity textoEntity = repository.recuperarTextoPorId(idTexto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de texto no encontrado"));
        AutorEntity autorEntity = repository.recuperarAutorPorId(idAutor)
                .orElseThrow(() -> new RecursoNoEncontradoException("Id de autor no encontrado"));
        if(textoEntity.getAutor()==null ||
                textoEntity.getAutor()!=autorEntity){
            throw  new RecursoNoVinculadoException();
        } else{
            textoEntity.setAutor(null);
            repository.guardarTexto(textoEntity);
        }
        TextoDto textoDespues = recuperarTextoPorId(idTexto);
        AutorDto autorDespues = recuperarAutorPorId(idAutor);
        return new TextoAutor(textoDespues, autorDespues);
    }

//ELIMINAR RECURSO
    @Override
    public void eliminarAutorPorId(int idAutor) {
        AutorEntity autorEntity = repository.recuperarAutorPorId(idAutor)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de autor no encontrado"));
        repository.eliminarAutor(autorEntity);
    }
    @Override
    public void eliminarTextoPorId(int idTexo) {
        TextoEntity textoEntity = repository.recuperarTextoPorId(idTexo)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de texto no encontrado"));
        repository.eliminarTexto(textoEntity);
    }
    @Override
    public void eliminarTemaPorId(int idTema) {
        TemaEntity temaEntity = repository.recuperarTemaPorId(idTema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de tema no encontrado"));
        repository.eliminarTema(temaEntity);
    }
    @Override
    public void eliminarSupertemaPorId(int idSupertema) {
        SupertemaEntity supertemaEntity = repository.recuperarSupertemaPorId(idSupertema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de supertema no encontrado"));
        repository.eliminarSupertema(supertemaEntity);
    }
//ACTUALIZAR RECURSO POR ID
    @Override
    public AutorDto actualizarAutorPorId(int idAutor, AutorNuevo autorNuevo) {
        AutorEntity autorEntity = repository.recuperarAutorPorId(idAutor)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de autor no encontrado"));
        //control de null en campos notNull
        if (Objects.isNull(autorNuevo.getNombreAutor())){
            throw new CampoEnBlancoException("nombreAutor");
        }
        autorEntity.setNombreAutor(autorNuevo.getNombreAutor());
        autorEntity.setFechaAutor(autorNuevo.getFechaAutor());
        autorEntity.setDescripcionBreve(autorNuevo.getDescripcionBreve());
        autorEntity.setDescripcionLarga(autorNuevo.getDescripcionLarga());
        repository.guardarAutor(autorEntity);
        AutorDto autorActualizado = recuperarAutorPorId(idAutor);
        return autorActualizado;
    }

    @Override
    public TextoDto actualizarTextoPorId(int idTexto, TextoNuevo textoNuevo) {
        TextoEntity textoEntity = repository.recuperarTextoPorId(idTexto)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de texto no encontrado"));
        if (textoEntity.getTextoString()==null){
            throw new CampoEnBlancoException("textoString");
        }
        textoEntity.setTextoString(textoNuevo.getTextoString());
        textoEntity.setLongitud(textoNuevo.getLongitud());
        repository.guardarTexto(textoEntity);
        TextoDto textoActualizado = recuperarTextoPorId(idTexto);
        return textoActualizado;
    }

    @Override
    public TemaDto actualizarTemaPorId(int idTema, TemaNuevo temaNuevo) {
        TemaEntity temaEntity = repository.recuperarTemaPorId(idTema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de texto no encontrado"));
        if (temaEntity.getNombreTema()==null){
            throw new CampoEnBlancoException("nombreTema");
        }
        temaEntity.setNombreTema(temaNuevo.getNombreTema());
        repository.guardarTema(temaEntity);
        TemaDto temaActualizado = recuperarTemaPorId(idTema);
        return temaActualizado;
    }

    @Override
    public SupertemaDto actualizarSupertemaPorId(int idSupertema, SupertemaNuevo supertemaNuevo) {
        SupertemaEntity supertemaEntity = repository.recuperarSupertemaPorId(idSupertema)
                .orElseThrow(()->new RecursoNoEncontradoException("Id de supertema no encontrado"));
        if(supertemaEntity.getNombreSupertema()==null){
            throw new CampoEnBlancoException("nombreSupertema");
        }
        supertemaEntity.setNombreSupertema(supertemaNuevo.getNombreSupertema());
        repository.guardarSupertema(supertemaEntity);
        SupertemaDto supertemaActualizado = recuperarSupertemaPorId(idSupertema);
        return supertemaActualizado;
    }


}