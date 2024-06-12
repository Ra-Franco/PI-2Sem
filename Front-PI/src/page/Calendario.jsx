import React, { useEffect, useState } from 'react';
import moment from 'moment';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import withDragAndDrop from 'react-big-calendar/lib/addons/dragAndDrop';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import 'react-big-calendar/lib/addons/dragAndDrop/styles.css';
import './components/Components-Calendario-css.css';
import axios from 'axios';
import { format } from 'date-fns';

import EventModal from './components/ModalEvent/EventModal';
import Adicionar from './components/adicionar/Adicionar';
import CustomTollbar from './components/CustomCalendar/CustomTollbar';
import FiltroAtividades from './components/filtro/FiltroAtividades.jsx';

const DragAndDropCalendar = withDragAndDrop(Calendar);
const localizer = momentLocalizer(moment);


const formatDate = (date) => {
    return format(new Date(date), 'dd-MM-yyyy');
};

function Calendario() {
    const [eventos, setEventos] = useState([]);
    const [eventoSelecionado, setEventoSelecionado] = useState(null);
    const [eventosFiltrados, setEventosFiltrados] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        const fetchEventos = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/agenda/${localStorage.getItem("Id")}/eventos`, {
                params:{        
                        dataIni: formatDate(new Date('2022-05-14')), 
                        dataFim: formatDate(moment().add(1, 'year').toDate()) 
                },
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem("token")}`
                    }
                });
                setEventos(response.data);
                setEventosFiltrados(response.data);
            } catch (error) {
                console.error('Erro ao buscar eventos:', error);
                alert('Erro ao buscar eventos');
            }
        };

        fetchEventos();
    }, []);

    const eventStyle = (event) => ({
        style: {
            backgroundColor: event.color,
        },
    });

    const moverEventos = (data) => {
        const { start, end } = data;
        const updatedEvents = eventos.map((event) => {
            if (event.id === data.event.id) {
                return {
                    ...event,
                    start: new Date(start),
                    end: new Date(end),
                };
            }
            return event;
        });
        setEventos(updatedEvents);
    };

    const handleEventClick = (evento) => {
        setEventoSelecionado(evento);
    };

    const handleEventClose = () => {
        setEventoSelecionado(null);
    };

    const handleAdicionar = async (novoEvento) => {
        try {
            const response = await axios.post(`http://localhost:8080/agenda/${localStorage.getItem("Id")}/eventos`, novoEvento, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                }
            });
            setEventos([...eventos, response.data]);
            setEventosFiltrados([...eventos, response.data]);
        } catch (error) {
            console.error('Erro ao adicionar evento:', error);
            alert('Erro ao adicionar evento');
        }
    };

    const handleEventDelete = async (eventId) => {
        try {
            await axios.delete(`http://localhost:8080/agenda/${localStorage.getItem("Id")}/eventos/${eventId}`, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                }
            });
            const updatedEvents = eventos.filter((event) => event.id !== eventId);
            setEventos(updatedEvents);
            setEventosFiltrados(updatedEvents);
            setEventoSelecionado(null);
        } catch (error) {
            console.error('Erro ao apagar evento:', error);
            alert('Erro ao apagar evento');
        }
    };

    const handleEventUpdate = async (updatedEvent) => {
        try {
            const response = await axios.put(`http://localhost:8080/agenda/${localStorage.getItem("Id")}/eventos/${updatedEvent.id}`, updatedEvent, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                }
            });
            const updatedEvents = eventos.map((event) =>
                event.id === updatedEvent.id ? response.data : event
            );
            setEventos(updatedEvents);
            setEventosFiltrados(updatedEvents);
            setEventoSelecionado(null);
        } catch (error) {
            console.error('Erro ao atualizar evento:', error);
            alert('Erro ao atualizar evento');
        }
    };

    const handleSelecionarAtividades = (atividadesSelecionadas) => {
        setEventosFiltrados(atividadesSelecionadas);
    };

    const handleOpenModal = () => {
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div className='tela'>
            <div className='toolbar p-4' style={{ maxHeight: '100vh', overflowY: 'auto' }}>
                <button onClick={handleOpenModal} style={{ padding: '10px 20px', borderRadius: '5px', border: 'none', backgroundColor: '#6d6f6d', color: 'white', cursor: 'pointer', fontSize: '16px', width: '100%' }}>Adicionar Evento</button>
                <Adicionar
                    isOpen={isModalOpen}
                    onRequestClose={handleCloseModal}
                    onAdicionar={handleAdicionar}
                />
                <FiltroAtividades atividades={eventos} onSelecionarAtividades={handleSelecionarAtividades} />
            </div>

            <div className='calendario'>
                <DragAndDropCalendar
                    defaultDate={moment().toDate()}
                    defaultView='month'
                    events={eventosFiltrados}
                    localizer={localizer}
                    resizable
                    onEventDrop={moverEventos}
                    onEventResize={moverEventos}
                    onSelectEvent={handleEventClick}
                    eventPropGetter={eventStyle}
                    components={{
                        toolbar: CustomTollbar,
                    }}
                    className='calendar'
                />
            </div>
            {eventoSelecionado && (
                <EventModal
                    evento={eventoSelecionado}
                    onClose={handleEventClose}
                    onDelete={handleEventDelete}
                    onUpdate={handleEventUpdate}
                />
            )}
        </div>
    );
}

export default Calendario;
