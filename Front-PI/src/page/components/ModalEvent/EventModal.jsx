import React, { useState } from 'react';
import { Modal, Button, Form, Collapse } from 'react-bootstrap';
import axios from 'axios';
import { format } from 'date-fns';

const EventModal = ({ evento, onClose, onDelete, onUpdate }) => {
    const [editedEvent, setEditedEvent] = useState({
        ...evento,
        dataIni: evento.dataIni ? new Date(evento.dataIni).toISOString().slice(0, -8) : '',
        dataFim: evento.dataFim ? new Date(evento.dataFim).toISOString().slice(0, -8) : '',
        cor: evento.cor || '#000000',
    });

    const formatDate = (date) => {
        return format(new Date(date), 'dd-MM-yyyy HH:mm');
    };
    const [collapsed, setCollapsed] = useState(true);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEditedEvent({ ...editedEvent, [name]: value });
    };

    const handleColorChange = (e) => {
        setEditedEvent({ ...editedEvent, cor: e.target.value });
    };

    const handleStartDateChange = (e) => {
        const startDate = new Date(e.target.value);
        if (startDate <= new Date(editedEvent.dataFim)) {
            setEditedEvent({ ...editedEvent, dataIni: startDate.toISOString().slice(0, -8) });
        }
    };

    const handleEndDateChange = (e) => {
        const endDate = new Date(e.target.value);
        if (endDate >= new Date(editedEvent.dataIni)) {
            setEditedEvent({ ...editedEvent, dataFim: endDate.toISOString().slice(0, -8) });
        }
    };

    const handleDelete = async () => {
        try {
            await axios.delete(`http://localhost:8080/agenda/${localStorage.getItem("Id")}/eventos/${evento.id}`, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                }
            });
            onDelete(evento.id);
        } catch (error) {
            console.error('Erro ao apagar evento:', error);
            alert('Erro ao apagar evento');
        }
    };

    const handleUpdate = async () => {
        try {
            console.log(editedEvent);
            console.log(evento);

            const id = Number(evento.id);
            const url = `http://localhost:8080/agenda/${localStorage.getItem("Id")}/eventos/${id}`;

            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                },
                body: JSON.stringify({
                    id: editedEvent.id,
                    title: editedEvent.title,
                    start: formatDate(editedEvent.start),
                    end: formatDate(editedEvent.end),
                    desc: editedEvent.desc,
                    color: editedEvent.color,
                    tipo: editedEvent.tipo,
                })
            });

            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            console.log(response)
            const data = await response.json();
            onUpdate(data);
            onClose();
        } catch (error) {
            console.error('Erro ao atualizar evento:', error);
            alert('Erro ao atualizar evento');
        }
    };

    const adjustDate = (date) => {
        const adjustedDate = new Date(date);
        adjustedDate.setHours(adjustedDate.getHours() - 3);
        return adjustedDate.toISOString().slice(0, -8);
    };

    return (
        <Modal show={true} onHide={onClose}>
            <Modal.Header>
                <Modal.Title>{editedEvent.title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group controlId="formTitle">
                        <Form.Label>Título</Form.Label>
                        <Form.Control type="text" name="title" value={editedEvent.title} onChange={handleInputChange} />
                    </Form.Group>
                    <Form.Group controlId="formDesc">
                        <Form.Label>Descrição</Form.Label>
                        <Form.Control as="textarea" rows={3} name="desc" value={editedEvent.desc} onChange={handleInputChange} />
                    </Form.Group>

                    <Collapse in={!collapsed}>
                        <div>
                            <Form.Group controlId="formInicio">
                                <Form.Label>Início</Form.Label>
                                <Form.Control type="datetime-local" name="start" value={editedEvent.start} onChange={handleStartDateChange} />
                            </Form.Group>

                            <Form.Group controlId="formEnd">
                                <Form.Label>Fim</Form.Label>
                                <Form.Control type="datetime-local" name="end" value={editedEvent.end} onChange={handleEndDateChange} />
                            </Form.Group>

                            <Form.Group controlId="formColor">
                                <Form.Label>Cor</Form.Label>
                                <Form.Control type="color" name="cor" value={editedEvent.cor} onChange={handleColorChange} />
                            </Form.Group>

                            <Form.Group controlId="formTipo">
                                <Form.Label>Tipo</Form.Label>
                                <Form.Control type="text" name="tipo" value={editedEvent.tipo} onChange={handleInputChange} />
                            </Form.Group>
                        </div>
                    </Collapse>
                </Form>
            </Modal.Body>
            <Modal.Footer className="justify-content-between">
                <Button variant="secondary" onClick={() => setCollapsed(!collapsed)}>
                    {!collapsed ? 'Ocultar Detalhes' : 'Mostrar Detalhes'}
                </Button>
                <Button variant="danger" onClick={handleDelete}>
                    Apagar
                </Button>
                <Button variant="primary" onClick={handleUpdate}>
                    Salvar Alterações
                </Button>
            </Modal.Footer>
        </Modal>
    );
};
export default EventModal;
