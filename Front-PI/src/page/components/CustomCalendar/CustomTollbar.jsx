import React, { useState } from 'react';

const CustomTollbar = ({ label, onView, onNavigate }) => {
    const [itemText, setItemText] = useState('agenda');

    const handleViewChange = (view) => {
        onView(view);
        setItemText(view);
    };

    return (
        <div className="toolbar-container">
            <h1 className='mesAno'>{label}</h1>

            <div className="dirtop">
                <div className="dropdown">
                    <button className='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-bs-toggle="dropdown" aria-expanded="false">
                        {itemText}
                    </button>
                    <ul className='dropdown-menu' aria-labelledby='dropdownMenuButton'>
                        <li>
                            <button className='dropdown-item' onClick={() => handleViewChange('month')}>Month</button>
                        </li>
                        <li>
                            <button className='dropdown-item' onClick={() => handleViewChange('agenda')}>Agenda</button>
                        </li>
                    </ul>
                </div>

                <div className="toolbar-navegation" style={{ marginLeft: '15px' }}>
                    <button className='btn btn-secondary btn-ls mr-2 border-0' onClick={() => onNavigate('TODAY')}>Today</button>
                    <button className='btn btn-sm mr-2 text-secondary' onClick={() => onNavigate('PREV')} style={{ marginLeft: '15px' }}><i className="bi bi-caret-left"></i></button>
                    <button className='btn btn-sm mr-2 text-secondary' onClick={() => onNavigate('NEXT')}><i className="bi bi-caret-right"></i></button>
                </div>
            </div>
        </div>
    );
};

export default CustomTollbar;
