import React, { useEffect, useState } from 'react';

export default function AdminUser() {
    const UserData = [
        { id: 1, email: 'a@naver.com', password: '1234', name: 'kim', phone: '01012341234', point: 0 },
        { id: 2, email: 'a@naver.com', password: '1234', name: 'kim', phone: '01022222222', point: 0 },
        { id: 3, email: 'a@naver.com', password: '1234', name: 'kim', phone: '01011111111', point: 0 },
    ]

    const [tableData, setTableData] = useState(UserData)
    const [searchFilter, setSearchFilter] = useState('id')
    const [searchWord, setSearchWord] = useState('')

    useEffect(() => {
        setTableData(UserData)
    }, [])

    const searchHandler = () => {
        const filters = {
            id: (v) => v.id == searchWord,
            email: (v) => v.email.includes(searchWord),
            name: (v) => v.name.includes(searchWord),
            phone: (v) => v.phone.includes(searchWord)
        };

        setTableData(UserData.filter(filters[searchFilter]));
    }

    return (
        <div className='admin-user'>
            <div className="admin-search">
                <select name="filter" id="" onChange={(e) => { setSearchFilter(e.target.value) }}>
                    <option value="id">고객ID</option>
                    <option value="email">이메일</option>
                    <option value="name">이름</option>
                    <option value="phone">전화번호</option>
                </select>
                <input type="text" placeholder='검색어 입력' onChange={(e) => { setSearchWord(e.target.value) }} />
                <button onClick={() => { searchHandler() }}>검색</button>
            </div>
            <table className="admin-user-list">
                <thead>
                    <tr className='admin-header'>
                        <th>고객ID</th>
                        <th>이메일</th>
                        <th>비밀번호</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>포인트</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        tableData.map((a, i) => {
                            return (
                                <tr className="admin-data" key={a.id}>
                                    <td>{a.id}</td>
                                    <td>{a.email}</td>
                                    <td>{a.password}</td>
                                    <td>{a.name}</td>
                                    <td>{a.phone}</td>
                                    <td>{a.point}</td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        </div>
    );
}

